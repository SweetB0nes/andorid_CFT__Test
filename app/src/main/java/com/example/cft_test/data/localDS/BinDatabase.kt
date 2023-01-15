package com.example.cft_test.data.localDS

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cft_test.data.localDS.entity.BinDb
import com.example.cft_test.data.localDS.dao.BinDao

@Database(entities = [BinDb::class], version = 1, exportSchema = false)
abstract class BinDatabase : RoomDatabase() {
    abstract fun getBinDao(): BinDao
}