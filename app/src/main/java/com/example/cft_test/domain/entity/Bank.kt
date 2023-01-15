package com.example.cft_test.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    var name: String? = null,
    var url: String? = null,
    var phone: String? = null,
    var city : String? = null
): Parcelable