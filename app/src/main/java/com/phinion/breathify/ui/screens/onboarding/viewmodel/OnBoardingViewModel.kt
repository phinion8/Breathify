package com.phinion.breathify.ui.screens.onboarding.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phinion.breathify.domain.models.Value
import com.phinion.breathify.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _userName = mutableStateOf("")
    val userName = _userName

    private val _selectedMusic: MutableStateFlow<Value?> = MutableStateFlow(null)
    val selectedMusic = _selectedMusic.asStateFlow()

    private val _selectedPace: MutableStateFlow<Value?> = MutableStateFlow(null)
    val selectedPace = _selectedPace.asStateFlow()

    private val _selectedBreakTime: MutableStateFlow<Value?> = MutableStateFlow(null)
    val selectedBreakTime = _selectedBreakTime.asStateFlow()

    private val _selectedGoal: MutableStateFlow<Value?> = MutableStateFlow(null)
    val selectedGoal = _selectedGoal.asStateFlow()

    fun updateUserName(value: String) {
        _userName.value = value
    }
    fun updateSelectedGoal(value: Value) {
        _selectedGoal.value = value
    }

    fun updateSelectedBreakTime(value: Value) {
        _selectedBreakTime.value = value
    }
    fun updateSelectedPace(value: Value) {
        _selectedPace.value = value
    }

    fun updateSelectedMusic(value: Value) {
        _selectedMusic.value = value
    }

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnBoardingUseCase.invoke(completed)
        }
    }


}