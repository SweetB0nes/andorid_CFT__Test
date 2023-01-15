package com.example.cft_test.data.remoteDS

import com.example.cft_test.data.mappers.Mapper
import com.example.cft_test.data.remoteDS.apiCall.BinApiCall
import com.example.cft_test.domain.BinFailure
import com.example.cft_test.domain.BinState
import com.example.cft_test.domain.BinSuccess
import com.example.cft_test.data.remoteDS.RemoteDSInterface
import javax.inject.Inject

class RemoteDS @Inject constructor(
    private val apiCall: BinApiCall,
    private val mapper: Mapper
) : RemoteDSInterface {

    override suspend fun loadBinInfo(bin: String): BinState {
        return try {
            val response = apiCall.loadInfo(bin)
            val binInfo = mapper.binResponseToBinInfo(response)
            BinSuccess(binInfo)
        } catch (e: Exception) {
            BinFailure(e)
        }
    }
}