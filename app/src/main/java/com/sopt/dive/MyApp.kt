package com.sopt.dive

import android.app.Application
import com.sopt.dive.data.local.UserPrefs

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        UserPrefs.init(this)
    }
}