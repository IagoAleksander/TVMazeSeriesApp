package com.iaz.tvmazeseriesapp.di

import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.viewmodel.FavoritesViewModel
import com.iaz.tvmazeseriesapp.viewmodel.HomeViewModel
import com.iaz.tvmazeseriesapp.viewmodel.PersonDetailsViewModel
import com.iaz.tvmazeseriesapp.viewmodel.ShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel { (id: Int, show: Show?) ->
        ShowDetailsViewModel(id, show, get())
    }

    viewModel { (id: Int) ->
        PersonDetailsViewModel(id, get())
    }

    viewModel { (ids: List<String>) ->
        FavoritesViewModel(ids, get())
    }
}