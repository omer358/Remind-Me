package com.example.remindme

import android.app.Application
import androidx.preference.PreferenceManager
import com.example.remindme.utils.ThemeHelper

class RemindMeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        val themePref =
            sharedPreferences.getString("themePref", ThemeHelper.DEFAULT_MODE)
        if (themePref != null) {
            ThemeHelper.applyTheme(themePref)
        }
    }

    companion object {
        private const val TAG = "RemindMeApplication"
    }
}