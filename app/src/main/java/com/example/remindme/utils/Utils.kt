package com.example.remindme.utils

import android.util.Log
import com.example.remindme.R
import com.example.remindme.database.People
import com.example.remindme.screens.people.PeopleViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
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

fun selectFemaleVector(): Int{
    val vectors = listOf(
        R.drawable.ic_f1,
        R.drawable.ic_f2,
        R.drawable.ic_f3,
        R.drawable.ic_f4,
        R.drawable.ic_f5
    ).shuffled()
    return vectors[0]
}

fun selectMaleVector():Int{
    val vectors = listOf(
        R.drawable.ic_m1,
        R.drawable.ic_m2,
        R.drawable.ic_m3,
        R.drawable.ic_m4,
        R.drawable.ic_m5
    ).shuffled()
    return vectors[0]
}

fun groupOfPeople() : List<People>{
    val arrayList = ArrayList<People>()
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "male",avatar = selectMaleVector()))
    arrayList.add(People(firstName = "Test",secondName = "Propse",place = "Home",time = "2020-07-01 15:59",note = "this element for test proposes",gender = "female",avatar = selectFemaleVector()))
    Log.i(PeopleViewModel.TAG, "groupOfPeople: ${arrayList.size}")
    arrayList.toList()
    return arrayList
}


