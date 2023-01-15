package com.example.cft_test.data.remoteDS.entity

import com.google.gson.annotations.SerializedName
import com.example.cft_test.data.remoteDS.entity.BinBank
import com.example.cft_test.data.remoteDS.entity.BinCountry
import com.example.cft_test.data.remoteDS.entity.BinNumber

data class BinResponse(
    @SerializedName("number")
    var binNumber: BinNumber? = null,

    @SerializedName("scheme")
    var scheme: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("brand")
    var brand: String? = null,

    @SerializedName("prepaid")
    var prepaid: String? = null,

    @SerializedName("country")
    var country: BinCountry? = null,

    @SerializedName("bank")
    var bank: BinBank? = null
)