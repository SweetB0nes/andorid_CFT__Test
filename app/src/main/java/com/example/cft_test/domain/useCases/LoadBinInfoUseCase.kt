package com.example.cft_test.domain.useCases

import com.example.cft_test.domain.entity.BinSource
import com.example.cft_test.domain.interfaces.BinRepositoryInterface
import javax.inject.Inject

class LoadBinInfoUseCase @Inject constructor(
    private val binRepositoryInterface: BinRepositoryInterface
) {
    suspend operator fun invoke(bin: BinSource) {
        binRepositoryInterface.loadBinInfo(bin)
    }
}