package com.example.remindme

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

private val sDateFormat = SimpleDateFormat("dd MMM")

private const val MINUTE_MILLIS = 1000 * 60.toLong()
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

fun convertDateToPassedTime(time: String): CharSequence? {
    val sdf = SimpleDateFormat("yyy-MM-dd HH:mm")
    val date = sdf.parse(time)
    val millis: Long = date.time
    val now = System.currentTimeMillis()


    var rightTime: String?

    if (now - millis < DAY_MILLIS) {
        if (now - millis < HOUR_MILLIS) {
            if (now - millis < MINUTE_MILLIS) {
                rightTime = "Now"
            } else {
                val minutes = (now - millis).toDouble().roundToInt() / MINUTE_MILLIS
                rightTime = minutes.toString() + "M"
                println("less than hour $rightTime")
            }
        } else {
            val minutes: Int = ((now - millis) / HOUR_MILLIS).toDouble().roundToInt()
            rightTime = minutes.toString() + "H"
            println("more than hour $rightTime")
        }
    } else {
        val dateDate = Date(millis)
        rightTime = sDateFormat.format(dateDate)
        println("more than day $rightTime")

    }
    // Add a dot to the date string
    val result = "\u2022 $rightTime"
    return result
}
