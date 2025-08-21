package com.iwelogic.jedyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JedyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Init code
    }
}