package com.iaz.tvmazeseriesapp

import android.app.Application
import com.iaz.tvmazeseriesapp.di.networkModule
import com.iaz.tvmazeseriesapp.di.repositoryModule
import com.iaz.tvmazeseriesapp.di.viewModelModule
import com.iaz.tvmazeseriesapp.repository.Prefs
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CustomApplication : Application() {

    companion object {
        var prefs: Prefs? = null
        lateinit var instance: CustomApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Prefs(applicationContext)

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