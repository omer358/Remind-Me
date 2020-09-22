package com.example.remindme.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.remindme.MainActivity
import com.example.remindme.R
import com.example.remindme.database.People

private const val NOTIFICATION_ID = 0
const val PEOPLE_REMINDER_NOTIFICATION_CHANNEL_ID = "people-reminder-id-channel"

/**
 * a helper extension function for showing the people as notification.
 * the function takes two parameters:
 * @param context
 * @param person represent the person we want to show.
 * */
fun NotificationManager.sendNotification(
    context: Context,
    person: People
) {
    val bundle = Bundle()
    bundle.putLong("personId", person.id)

    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        createNotificationChannel(context)
    }

    val pendingIntent = NavDeepLinkBuilder(context)
        .setComponentName(MainActivity::class.java)
        .setGraph(R.navigation.nav_graph)
        .setDestination(R.id.personDetailsFragment)
        .setArguments(bundle)
        .createPendingIntent()

    val builder = NotificationCompat.Builder(context, PEOPLE_REMINDER_NOTIFICATION_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_avatar_1)
        .setContentTitle(person.firstName + " " + person.secondName)
        .setContentText(fillContent(context, person))
        .setChannelId(PEOPLE_REMINDER_NOTIFICATION_CHANNEL_ID)
        .setColor(ContextCompat.getColor(context, R.color.primaryColor))
        .setDefaults(Notification.DEFAULT_SOUND)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
        && Build.VERSION.SDK_INT < Build.VERSION_CODES.O
    ) {
        builder.priority = NotificationCompat.PRIORITY_HIGH
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val bitmap = context.vectorToBitmap(person.avatar)
        builder.setLargeIcon(bitmap)
    }
    notify(NOTIFICATION_ID, builder.build())
}

private fun fillContent(context: Context,person: People): String {
    return context.getString(R.string.notification_messages,person.firstName,person.place)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun createNotificationChannel(context: Context) {

    val descriptionText = "to remind you of people "
    val importance = NotificationManager.IMPORTANCE_HIGH
    val peopleChannel = NotificationChannel(
        PEOPLE_REMINDER_NOTIFICATION_CHANNEL_ID,
        context.getString(R.string.main_notification_channel_name),
        importance
    ).apply {
        description = descriptionText
    }
    // Register the channel with the system
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(peopleChannel)
}

/**
 * a helper extension function for creating a vector for the notification if
 * the current device is higher than API level 21
 * */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.vectorToBitmap(drawableId: Int): Bitmap? {
    val drawable = getDrawable(drawableId) ?: return null
    val bitmap = createBitmap(
        drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    ) ?: return null
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}