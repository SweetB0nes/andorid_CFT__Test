package com.example.cft_test.domain.useCases

import com.example.cft_test.domain.interfaces.BinRepositoryInterface
import javax.inject.Inject

class GetHistoryOfRequestsUseCase @Inject constructor(
    private val binRepositoryInterface: BinRepositoryInterface
) {
    suspend operator fun invoke(): List<String>{
        return binRepositoryInterface.getHistoryOfRequests()
    }
}