package com.learnandroid.module

import com.learnandroid.PreferenceUtil
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val prefModule = module {
    single{
        PreferenceUtil(androidApplication())
    }
}