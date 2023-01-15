package com.example.cft_test.domain.useCases

import com.example.cft_test.domain.interfaces.BinRepositoryInterface
import javax.inject.Inject

class ClearInfoFlowUseCase @Inject constructor(
    private val binRepositoryInterface: BinRepositoryInterface
) {
    operator fun invoke(){
        binRepositoryInterface.clearInfoFlow()
    }
}