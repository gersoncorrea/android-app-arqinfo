package com.app.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

 class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SampleApplication)
            modules(RouterModule.routerAppModule)
        }
    }
}