package com.phinion.breathify.domain.use_cases

import com.phinion.breathify.domain.use_cases.breath_data.GetBreathingDataUseCase
import com.phinion.breathify.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.phinion.breathify.domain.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getBreathingDataUseCase: GetBreathingDataUseCase
)