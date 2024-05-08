package com.phinion.breathify.domain.use_cases.save_onboarding

import com.phinion.breathify.data.repository.Repository


class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(completed: Boolean){
        repository.saveOnBoardingState(completed = completed)
    }

}