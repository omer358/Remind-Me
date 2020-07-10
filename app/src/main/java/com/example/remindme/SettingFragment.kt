package com.example.remindme

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?,rootKey: String?) {
       setPreferencesFromResource(R.xml.preference_screen,rootKey)

        val themePreference =
            findPreference<ListPreference>("themePref")
        if (themePreference != null) {
            themePreference.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference, newValue ->
                    val themeOption = newValue as String
                    ThemeHelper.applyTheme(themeOption)
                    true
                }
        }

    }

}