package com.example.cft_test.domain.useCases

import com.example.cft_test.domain.BinState
import com.example.cft_test.domain.interfaces.BinRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInfoFlowUseCase @Inject constructor(
    private val binRepositoryInterface: BinRepositoryInterface
){
    operator fun invoke(): Flow<BinState>{
        return binRepositoryInterface.getInfoFlow()
    }
}