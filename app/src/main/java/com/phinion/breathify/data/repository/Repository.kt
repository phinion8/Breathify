package com.phinion.breathify.data.repository

import com.phinion.breathify.data.remote.BreathApi
import com.phinion.breathify.domain.models.BreathingData
import com.phinion.breathify.domain.repository.PreferenceManager
import com.phinion.breathify.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(
    private val preferenceManager: PreferenceManager,
    private val breathApi: BreathApi
) {

    suspend fun getBreathingData(uid: String, date: String): Resource<BreathingData>{
        val response = try {
            breathApi.getBreathingData(uid, date)
        }catch (e: Exception){
            return Resource.Error(e.message)
        }
        return Resource.Success(response)
    }


    suspend fun saveOnBoardingState(completed: Boolean){
        preferenceManager.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return preferenceManager.readOnBoardingState()
    }

}