package com.learnandroid.module

import com.learnandroid.ui.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { (param : String) -> MainViewModel(get(), param) }

}