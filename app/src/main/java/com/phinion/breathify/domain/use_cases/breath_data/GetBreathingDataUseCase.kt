package com.phinion.breathify.domain.use_cases.breath_data

import com.phinion.breathify.data.repository.Repository
import com.phinion.breathify.domain.models.BreathingData
import com.phinion.breathify.utils.Resource
import javax.inject.Inject

class GetBreathingDataUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(uid: String, date: String): Resource<BreathingData>{
        return repository.getBreathingData(uid, date)
    }

}