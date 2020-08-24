package com.example.remindme.notifications

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.remindme.MainActivity
import com.example.remindme.R
import com.example.remindme.database.People

private const val NOTIFICATION_ID = 0
/**
 * a helper extension function for showing the people as notification.
 * the function takes two parameters:
 * @param context
 * @param person represent the person we want to show.
 * */
fun NotificationManager.sendNotification(
    context:Context,
    person:People)
{
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

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        val bitmap = context.vectorToBitmap(person.avatar)
        builder.setLargeIcon(bitmap)
    }
    notify(NOTIFICATION_ID,builder.build())
}

/**
 * a helper extension function for creating a vector for the notification if
 * the current device is higher than API level 21
 * */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.vectorToBitmap(drawableId: Int): Bitmap? {
    val drawable = getDrawable( drawableId) ?: return null
    val bitmap = createBitmap(
        drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    ) ?: return null
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}