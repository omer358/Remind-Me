package com.example.remindme

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.remindme.database.People

private const val NOTIFICATION_ID = 0
private const val REQUEST_CODE = 0

fun NotificationManager.sendNotification(
    context:Context,
    person:People)
{
    val intent = Intent(context.applicationContext, MainActivity::class.java).apply {
        putExtra("personId",person.id)
    }
    val bundle = Bundle()
    bundle.putLong("personId",person.id)

    val pendingIntent =  NavDeepLinkBuilder(context)
        .setComponentName(MainActivity::class.java)
        .setGraph(R.navigation.nav_graph)
        .setDestination(R.id.personDetailsFragment)
        .setArguments(bundle)
        .createPendingIntent()

    val builder = NotificationCompat.Builder(context,"id")
        .setSmallIcon(R.drawable.ic_avatar_1)
        .setContentTitle(person.firstName+" "+person.secondName)
        .setContentText("Do you remember ${person.firstName}, you met him at ${person.place}")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setChannelId("id")
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    notify(NOTIFICATION_ID,builder)
}