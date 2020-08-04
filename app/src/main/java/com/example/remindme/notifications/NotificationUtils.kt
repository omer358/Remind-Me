package com.example.remindme.notifications

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
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

    val bitmap = context.vectorToBitmap(person.avatar)

    val builder = NotificationCompat.Builder(context,"id")
        .setSmallIcon(R.drawable.ic_avatar_1)
        .setLargeIcon(bitmap)
        .setContentTitle(person.firstName+" "+person.secondName)
        .setContentText("Do you remember ${person.firstName}, you met him at ${person.place}")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setChannelId("id")
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    notify(NOTIFICATION_ID,builder)
}

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