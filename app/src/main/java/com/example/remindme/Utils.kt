package com.example.remindme

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

private val sDateFormat = SimpleDateFormat("dd MMM")

private const val MINUTE_MILLIS = 1000 * 60.toLong()
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

fun convertDateToPassedTime(time: Long): CharSequence? {

    val now = System.currentTimeMillis()

    var rightTime: String?

    if (now - time < DAY_MILLIS) {
        if (now - time < HOUR_MILLIS) {
            if (now - time < MINUTE_MILLIS) {
                rightTime = "Now"
            } else {
                val minutes = (now - time).toDouble().roundToInt() / MINUTE_MILLIS
                rightTime = minutes.toString() + "M"
                println("less than hour $rightTime")
            }
        } else {
            val minutes: Int = ((now - time) / HOUR_MILLIS).toDouble().roundToInt()
            rightTime = minutes.toString() + "H"
            println("more than hour $rightTime")
        }
    } else {
        val dateDate = Date(time)
        rightTime = sDateFormat.format(dateDate)
        println("more than day $rightTime")

    }
    // Add a dot to the date string
    val result = "\u2022 $rightTime"
    return result
}
