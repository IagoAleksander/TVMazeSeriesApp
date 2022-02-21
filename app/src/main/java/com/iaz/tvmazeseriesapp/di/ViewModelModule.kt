package com.iaz.tvmazeseriesapp.di

import com.iaz.tvmazeseriesapp.viewmodel.HomeViewModel
import com.iaz.tvmazeseriesapp.viewmodel.PersonDetailsViewModel
import com.iaz.tvmazeseriesapp.viewmodel.ShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel { (id: Int) ->
        ShowDetailsViewModel(id, get())
    }

    viewModel { (id: Int) ->
        PersonDetailsViewModel(id, get())
    }
}