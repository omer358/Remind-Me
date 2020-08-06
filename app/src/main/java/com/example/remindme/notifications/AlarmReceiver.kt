package com.example.remindme.notifications

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.room.RoomDatabase
import com.example.remindme.database.PeopleDatabase
import com.google.android.material.snackbar.Snackbar

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?)
    {

        Toast.makeText(context,
            "broadcast Receiver",
            Toast.LENGTH_SHORT).show()

    }
}