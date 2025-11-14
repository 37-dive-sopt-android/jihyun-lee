package com.sopt.dive

import android.app.Application
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.di.AppContainer

class DiveApp : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
        UserPrefs.init(this)
    }
}