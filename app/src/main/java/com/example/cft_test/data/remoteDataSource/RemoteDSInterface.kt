package com.example.cft_test.data.remoteDS

import com.example.cft_test.domain.BinState

interface RemoteDSInterface {
    suspend fun loadBinInfo(bin: String): BinState
}