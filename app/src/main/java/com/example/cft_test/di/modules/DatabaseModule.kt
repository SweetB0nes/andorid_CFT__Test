package com.example.cft_test.di.modules

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cft_test.data.localDS.BinDatabase
import com.example.cft_test.data.localDS.dao.BinDao
import com.example.cft_test.data.localDS.entity.BinDb
import com.example.cft_test.di.ApplicationScope

import dagger.Module
import dagger.Provides

@Module
interface DatabaseModule {
    companion object{
        @ApplicationScope
        @Provides
        fun provideBinDatabase(
            application: Application
        ): BinDatabase{
            return Room.databaseBuilder(
                application.applicationContext,
                BinDatabase::class.java,
                "Bin_db"
            ).build()
        }

        @ApplicationScope
        @Provides
        fun provideDao(room: BinDatabase): BinDao{
            return room.getBinDao()
        }
    }
}