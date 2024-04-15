package com.example.shopmie.ui.base

import android.app.Application
import com.example.shopmie.injection.dataBaseModule
import com.example.shopmie.injection.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()
        initKoin()

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(getDependencies())
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun getDependencies() = listOf(dataBaseModule, homeModule)
}
