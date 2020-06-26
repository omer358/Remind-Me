package com.example.remindme

import android.content.res.Resources
import android.graphics.drawable.Drawable


/**
 * Returns a string representing the numeric quality rating.
 */
fun convertStringGenderToImage(gender: String, resources: Resources): Drawable {
    var genderImage = resources.getDrawable(R.drawable.ic_baseline_emoji_emotions_30)
    when (gender) {
        "Male" -> genderImage = resources.getDrawable(R.drawable.ic_baseline_emoji_emotions_30)
        "Female" -> genderImage = resources.getDrawable(R.drawable.ic_baseline_sentiment_very_satisfied_30)

    }
    return genderImage
}