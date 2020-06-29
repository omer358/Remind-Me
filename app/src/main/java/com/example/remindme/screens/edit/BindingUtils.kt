package com.example.remindme.screens.edit

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.remindme.R
import com.example.remindme.convertDateToPassedTime
import com.example.remindme.database.People

//@BindingAdapter
//fun TextView.setPersonName(people: People){
//    people.let {
//        text = it.firstName
//    }
//}
//
//@BindingAdapter
//fun  TextView.setTime(people: People){
//    people.let {
//        text = convertDateToPassedTime(it.time)
//    }
//}
//@BindingAdapter
//fun ImageView.setGenderImage(people: People){
//    setImageResource(
//        when (people.gender) {
//        "Male" -> R.drawable.ic_baseline_emoji_emotions_30
//        "Female" -> R.drawable.ic_baseline_sentiment_very_satisfied_30
//        else -> R.drawable.ic_baseline_person_outline_24
//    })
//}