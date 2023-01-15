package com.example.cft_test.domain.interfaces

import com.example.cft_test.domain.BinState
import com.example.cft_test.domain.entity.BinInfo
import com.example.cft_test.domain.entity.BinSource
import kotlinx.coroutines.flow.Flow

interface BinRepositoryInterface {
    suspend fun loadBinInfo(bin: BinSource)
    suspend fun getHistoryOfRequests(): List<String>
    fun getInfoFlow(): Flow<BinState>
    fun clearInfoFlow()
}