package com.example.cft_test.di

import android.app.Application
import com.example.cft_test.App
import com.example.cft_test.di.modules.DataModule
import com.example.cft_test.di.modules.DatabaseModule
import com.example.cft_test.di.modules.NetworkModule
import com.example.cft_test.di.modules.ViewModelModule
import com.example.cft_test.presentation.fragments.BinInfoFragment
import com.example.cft_test.presentation.fragments.InitialFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class, DataModule::class, NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {
    fun injectApplication(app: App)
    fun injectInitialFragment(initialFragment: InitialFragment)
    fun injectBinInfoFragment(binInfoFragment: BinInfoFragment)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance app: Application
        ):ApplicationComponent
    }
}