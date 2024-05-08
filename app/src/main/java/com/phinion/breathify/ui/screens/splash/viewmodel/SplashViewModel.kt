package com.phinion.breathify.ui.screens.splash.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phinion.breathify.data.remote.AppConfig
import com.phinion.breathify.domain.use_cases.UseCases
import com.phinion.breathify.utils.Constants
import com.phinion.breathify.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _onBoardingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val onBoardingState = _onBoardingState.asStateFlow()
    private val _loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()
    val error = mutableStateOf("")
    private val _errorState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorState = _errorState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingState.value = useCases.readOnBoardingUseCase().stateIn(this@launch).value
        }
        getBreathingData()
    }

    private fun getBreathingData(){
        viewModelScope.launch {
            val result = useCases.getBreathingDataUseCase(Constants.USER_ID, System.currentTimeMillis().toString())
            when(result){
                is Resource.Loading -> {
                    _loadingState.value = true
                }
                is Resource.Success -> {
                    result.data?.let { AppConfig.setAppConfiguration(it) }
                    _loadingState.value = false
                }
                is Resource.Error -> {
                    _loadingState.value = false
                    _errorState.value = true
                    error.value = result.data?.status?.err ?: "Something went wrong!"
                }
            }

        }
    }
}