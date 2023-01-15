package com.example.cft_test.data.remoteDS.entity

import com.google.gson.annotations.SerializedName

data class BinCountry(
    @SerializedName("numeric")
    var numeric: String?,

    @SerializedName("alpha2")
    var alpha2: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("emoji")
    var emoji: String? = null,

    @SerializedName("currency")
    var currency: String? = null,

    @SerializedName("latitude")
    var latitude: Double? = null,

    @SerializedName("longitude")
    var longitude: Double? = null
)