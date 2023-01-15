package com.example.cft_test.data.repository

import com.example.cft_test.data.localDS.dao.BinDao
import com.example.cft_test.data.localDS.entity.BinDb
import com.example.cft_test.data.mappers.Mapper
import com.example.cft_test.data.remoteDS.RemoteDSInterface
import com.example.cft_test.domain.BinInitial
import com.example.cft_test.domain.BinLoading
import com.example.cft_test.domain.BinState
import com.example.cft_test.domain.BinSuccess
import com.example.cft_test.domain.entity.BinSource
import com.example.cft_test.domain.interfaces.BinRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class BinRepository @Inject constructor(
    private val remoteDataSource: RemoteDSInterface,
    private val binDao: BinDao,
    private val mapper: Mapper,
) : BinRepositoryInterface {

    private val infoMutableStateFlow: MutableStateFlow<BinState> = MutableStateFlow(BinInitial)

    //when a BIN arrives, it is checked if there is one in the database, if not, then a request is sent, if it is, then information from the database is sent
    override suspend fun loadBinInfo(bin: BinSource) {
        val checkFromDb = checkBin(bin)
        if (checkFromDb != null) {
            infoMutableStateFlow.value = BinSuccess(mapper.dbEntityToBinInfo(checkFromDb))
        } else {
            infoMutableStateFlow.value = BinLoading
            val result = remoteDataSource.loadBinInfo(bin.bin)
            writeToDb(result, bin.bin)
            infoMutableStateFlow.value = result
        }
    }

    private suspend fun checkBin(bin: BinSource): BinDb? {
        return binDao.getBinFromId(bin.bin)
    }

    private suspend fun writeToDb(binState: BinState, binNumber: String) {
        if (binState !is BinSuccess) {
            return
        }
        binDao.addNewBin(mapper.binInfoToBinDb(binState.binInfo, binNumber))
    }

    override suspend fun getHistoryOfRequests(): List<String> {
        val dbResult = binDao.getAllFromDb()
        return mapper.listFromDbToListBinInfo(dbResult)
    }

    override fun getInfoFlow(): Flow<BinState> {
        return infoMutableStateFlow.asStateFlow()
    }

    override fun clearInfoFlow() {
        infoMutableStateFlow.value = BinInitial
    }
}