package com.example.cft_test.di.modules

import com.example.cft_test.data.remoteDS.RemoteDS
import com.example.cft_test.data.remoteDS.RemoteDSInterface
import com.example.cft_test.data.mappers.Mapper
import com.example.cft_test.data.repository.BinRepository
import com.example.cft_test.di.ApplicationScope
import com.example.cft_test.domain.interfaces.BinRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindBinRepository(impl: BinRepository): BinRepositoryInterface

    @ApplicationScope
    @Binds
    fun bindRemoteDataSource(impl: RemoteDS): RemoteDSInterface

    companion object{
        @ApplicationScope
        @Provides
        fun provideMapper(): Mapper {
            return Mapper()
        }
    }
}