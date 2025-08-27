package com.iwelogic.jedyapp.di

import android.content.*
import dagger.hilt.android.qualifiers.*
import javax.inject.*
import androidx.core.content.edit

@Singleton
class SettingStorage @Inject constructor(
    @ApplicationContext context: Context
) {
    companion object {
        private const val SETTINGS_STORAGE_KEY = "SETTINGS_STORAGE_KEY"
        private const val OPEN_COUNTER = "OPEN_COUNTER_KEY"
    }

    private val preferences : SharedPreferences = context.getSharedPreferences(SETTINGS_STORAGE_KEY, Context.MODE_PRIVATE)


    var openCounter: Int
        set(value) {
            preferences.edit(commit = true) { putInt(OPEN_COUNTER, value) }
        }
        get() = preferences.getInt(OPEN_COUNTER, 0)

    fun openApp(){
        openCounter++
    }
}