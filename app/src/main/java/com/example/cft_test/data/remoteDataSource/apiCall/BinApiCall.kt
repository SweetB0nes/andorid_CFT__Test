package com.example.cft_test.data.remoteDS.apiCall


import com.example.cft_test.data.remoteDS.entity.BinResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApiCall {

    @GET("{$BIN}")
    suspend fun loadInfo(
        @Path(BIN) bin: String
    ): BinResponse

    companion object {
        private const val BIN = "bin"
    }
}