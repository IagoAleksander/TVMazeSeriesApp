package com.iaz.tvmazeseriesapp

import android.app.Application
import com.iaz.tvmazeseriesapp.di.networkModule
import com.iaz.tvmazeseriesapp.di.repositoryModule
import com.iaz.tvmazeseriesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CustomApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}