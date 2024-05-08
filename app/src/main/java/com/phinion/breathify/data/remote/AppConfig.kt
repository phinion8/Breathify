package com.phinion.breathify.data.remote

import com.phinion.breathify.domain.models.BreathingData

object AppConfig {
    private lateinit var breathingData: BreathingData
    fun getAppConfiguration(): BreathingData{
        return breathingData
    }

    fun setAppConfiguration(breathingData: BreathingData){
        this.breathingData = breathingData
    }
}