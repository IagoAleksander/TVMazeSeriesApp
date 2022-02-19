package com.iaz.tvmazeseriesapp.di

import com.iaz.tvmazeseriesapp.repository.service.FetchShowsService
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {

    factory<FetchShowsService> {
        get<Retrofit>().create(FetchShowsService::class.java)
    }
}