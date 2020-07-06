package com.example.remindme

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?,rootKey: String?) {

       setPreferencesFromResource(R.xml.preference_screen,rootKey)
    }

}