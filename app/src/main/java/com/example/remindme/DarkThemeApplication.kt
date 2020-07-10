package com.example.remindme

import android.app.Application
import androidx.preference.PreferenceManager

class DarkThemeApplication: Application() {

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
}