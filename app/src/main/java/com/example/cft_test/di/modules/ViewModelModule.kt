package com.example.cft_test.di.modules

import androidx.lifecycle.ViewModel
import com.example.cft_test.di.ViewModelKey
import com.example.cft_test.presentation.viewModels.BinInfoViewModel
import com.example.cft_test.presentation.viewModels.InitialViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(BinInfoViewModel::class)
    @Binds
    fun bindBinInfoViewModel(impl: BinInfoViewModel): ViewModel

    @IntoMap
    @ViewModelKey(InitialViewModel::class)
    @Binds
    fun bindInitialViewModel(impl: InitialViewModel): ViewModel
}