package com.example.remindme

import java.text.SimpleDateFormat
import java.util.*

private val sDateFormat = SimpleDateFormat("dd MMM")


private const val MINUTE_MILLIS = 1000 * 60.toLong()
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

fun convertDateToPassedTime(time: String): CharSequence? {
    val sdf = SimpleDateFormat.getDateTimeInstance()
    val date = sdf.parse(time)
    val millis: Long = date.time
    val now = System.currentTimeMillis()


    var rightTime:String
    rightTime = if (now - millis < DAY_MILLIS){
        if (now - millis < HOUR_MILLIS){
            val minutes = Math.round((now - millis).toDouble())/ MINUTE_MILLIS
            minutes.toString()+"M"
        }else{
            val minutes: Long =  Math.round(((now - millis) / HOUR_MILLIS).toDouble());
            minutes.toString()+"H"
        }
    }else  {
        val dateDate = Date(millis);
        sDateFormat.format(dateDate);

    }
    // Add a dot to the date string
    rightTime = "\u2022 $date";
    return rightTime
}
