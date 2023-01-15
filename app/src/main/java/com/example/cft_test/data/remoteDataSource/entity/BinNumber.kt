package com.example.cft_test.data.remoteDS.entity

import com.google.gson.annotations.SerializedName

data class BinNumber(
    @SerializedName("length")
    var len: Int? = null,

    @SerializedName("luhn")
    var luhn: Boolean? = null

)