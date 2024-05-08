package com.phinion.breathify.data.remote

import com.phinion.breathify.domain.models.BreathingData
import com.phinion.breathify.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface BreathApi {

    @GET("tools/breathing/get")
    suspend fun getBreathingData(
        @Query("uid") uid: String,
        @Query("date") date: String
    ): BreathingData

}