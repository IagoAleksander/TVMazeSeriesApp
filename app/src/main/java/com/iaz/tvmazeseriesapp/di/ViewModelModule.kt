package com.iaz.tvmazeseriesapp.di

import com.iaz.tvmazeseriesapp.viewmodel.HomeShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeShowViewModel(get())
    }
}