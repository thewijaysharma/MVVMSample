package com.learnandroid.base

import android.app.Application
import android.content.Context
import com.learnandroid.module.prefModule
import com.learnandroid.module.retrofitModule
import com.learnandroid.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(retrofitModule, prefModule, viewModelModule))
        }
    }

}