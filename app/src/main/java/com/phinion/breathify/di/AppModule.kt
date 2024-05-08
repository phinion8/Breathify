package com.phinion.breathify.di

import android.content.Context
import com.phinion.breathify.data.remote.BreathApi
import com.phinion.breathify.data.repository.PreferenceManagerImpl
import com.phinion.breathify.data.repository.Repository
import com.phinion.breathify.domain.repository.PreferenceManager
import com.phinion.breathify.domain.use_cases.UseCases
import com.phinion.breathify.domain.use_cases.breath_data.GetBreathingDataUseCase
import com.phinion.breathify.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.phinion.breathify.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.phinion.breathify.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context: Context): PreferenceManager {
        return PreferenceManagerImpl(context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getBreathingDataUseCase = GetBreathingDataUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideBreathApi(): BreathApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).hostnameVerifier{_,_->
            true
        }.build()
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(BreathApi::class.java)
    }

}