package com.example.cft_test.data.localDS.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cft_test.data.localDS.entity.BinDb

@Dao
interface BinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewBin(bin: BinDb)

    @Query("SELECT * FROM Bin_db")
    suspend fun getAllFromDb(): List<BinDb>

    @Query("SELECT * FROM Bin_db WHERE id == :id LIMIT 1")
    suspend fun getBinFromId(id: String): BinDb?
}