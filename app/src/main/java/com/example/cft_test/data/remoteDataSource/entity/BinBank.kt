package com.example.cft_test.data.remoteDS.entity

import com.google.gson.annotations.SerializedName

data class BinBank (
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("phone")
    var phone: String? = null,

    @SerializedName("city")
    var city : String? = null
)