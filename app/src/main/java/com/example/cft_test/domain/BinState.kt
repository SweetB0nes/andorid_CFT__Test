package com.example.cft_test.domain

import com.example.cft_test.domain.entity.BinInfo
import java.lang.Exception

sealed class BinState

data class BinSuccess(val binInfo: BinInfo): BinState()
data class BinFailure(val e: Exception): BinState()
object BinLoading: BinState()
object BinInitial: BinState()