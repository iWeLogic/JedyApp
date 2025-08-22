package com.iwelogic.jedyapp

import android.app.Application
import com.iwelogic.jedyapp.di.SettingStorage
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class JedyApp : Application() {

    @Inject
    lateinit var settingStorage: SettingStorage

    override fun onCreate() {
        super.onCreate()
        settingStorage.openApp()
    }
}