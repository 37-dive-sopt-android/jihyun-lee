package com.sopt.dive

import android.app.Application
import com.sopt.dive.core.di.AppContainer
import timber.log.Timber

class DiveApp : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        appContainer = AppContainer(this)
    }
}