package com.example.cft_test

import android.app.Application
import com.example.cft_test.di.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}