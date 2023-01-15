package com.example.cft_test.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.cft_test.R
import com.example.cft_test.domain.BinFailure
import com.example.cft_test.domain.BinState
import com.example.cft_test.domain.entity.BinHistory
import com.example.cft_test.domain.entity.BinSource
import com.example.cft_test.domain.useCases.ClearInfoFlowUseCase
import com.example.cft_test.domain.useCases.GetHistoryOfRequestsUseCase
import com.example.cft_test.domain.useCases.GetInfoFlowUseCase
import com.example.cft_test.domain.useCases.LoadBinInfoUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

/***
 * view model of the starter framgent interacting with the repository
 */

class InitialViewModel @Inject constructor(
    private val loadBinInfoUseCase: LoadBinInfoUseCase,
    private val getInfoFlowUseCase: GetInfoFlowUseCase,
    private val clearInfoFlowUseCase: ClearInfoFlowUseCase,
    private val getHistoryOfRequestsUseCase: GetHistoryOfRequestsUseCase,
) : ViewModel() {

    fun clearFlow() {
        clearInfoFlowUseCase.invoke()
    }

    suspend fun loadHistory(): List<BinHistory> {
        val strings = getHistoryOfRequestsUseCase.invoke()
        val resultList = arrayListOf<BinHistory>()
        for (s in strings) {
            resultList.add(BinHistory(s))
        }
        return resultList
    }

    fun showError(binFailure: BinFailure): Int {
        return when (binFailure.e) {
            is HttpException -> R.string.notFounded
            else -> R.string.connectionError
        }
    }

    fun getInfoFlow(): Flow<BinState> {
        return getInfoFlowUseCase.invoke()
    }

    suspend fun sendBin(bin: String) {
        loadBinInfoUseCase.invoke(BinSource(bin))
    }
}