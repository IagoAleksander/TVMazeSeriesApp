package com.iaz.tvmazeseriesapp.di

import com.iaz.tvmazeseriesapp.repository.PeopleRepository
import com.iaz.tvmazeseriesapp.repository.PeopleRepositoryImpl
import com.iaz.tvmazeseriesapp.repository.ShowsRepository
import com.iaz.tvmazeseriesapp.repository.ShowsRepositoryImpl
import com.iaz.tvmazeseriesapp.repository.service.FetchPeopleService
import com.iaz.tvmazeseriesapp.repository.service.FetchShowsService
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {

    factory<FetchShowsService> {
        get<Retrofit>().create(FetchShowsService::class.java)
    }

    factory<FetchPeopleService> {
        get<Retrofit>().create(FetchPeopleService::class.java)
    }

    single<ShowsRepository> {
        ShowsRepositoryImpl(get())
    }

    single<PeopleRepository> {
        PeopleRepositoryImpl(get())
    }
}