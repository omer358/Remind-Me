package com.example.remindme

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.remindme.notifications.AlarmReceiver
import com.example.remindme.utils.ThemeHelper

class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?,rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey)

        val themePreference =
            findPreference<ListPreference>("themePref")
        if (themePreference != null) {
            themePreference.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _, newValue ->
                    val themeOption = newValue as String
                    ThemeHelper.applyTheme(themeOption)
                    true
                }
        }
        val notificationPreference = findPreference<SwitchPreferenceCompat>("omo")
        if (notificationPreference != null) {
            notificationPreference.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { _, newValue ->
                    val switch: Boolean = newValue as Boolean
                    if (switch) {
                        initializeTheAlarm()
                    } else {
                        Toast.makeText(context, "no notification!", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
        }
    }

    private fun initializeTheAlarm() {
        val alarmManager =
            context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        alarmManager?.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME,
            SystemClock.elapsedRealtime() + 5000,
            6000,
            pendingIntent
        )
    }
}