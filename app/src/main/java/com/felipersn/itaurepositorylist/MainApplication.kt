package com.felipersn.itaurepositorylist

import android.app.Application
import com.felipersn.itaurepositorylist.common.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initDependencyInjectionModules()
    }

    //Start dependency injection modules
    private fun initDependencyInjectionModules() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    adapterModule,
                    networkModule,
                    serviceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}