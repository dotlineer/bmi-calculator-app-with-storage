package com.example.bmi.logic

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import com.example.bmi.R

val VERY_UNDERWEIGHT_UPPER_BOUND = 16.5
val UNDERWEIGHT_UPPER_BOUND = 18.5
val NORMALWEIGHT_UPPER_BOUND = 25.0
val OVERWEIGHT_UPPER_BOUND = 30.0
val OBESE_UPPER_BOUND = Double.MAX_VALUE


fun colorTextViewBasedOnScore(bmiValue: Double, tv: TextView) {
    if (bmiValue < VERY_UNDERWEIGHT_UPPER_BOUND) {
        tv.setTextColor(Color.parseColor("#3366cc"))
    }
    else if (bmiValue < UNDERWEIGHT_UPPER_BOUND) {
        tv.setTextColor(Color.parseColor("3ddff4"))
    }
    else if (bmiValue < NORMALWEIGHT_UPPER_BOUND) {
        tv.setTextColor(Color.parseColor("#00a693"))
    }
    else if (bmiValue < OVERWEIGHT_UPPER_BOUND) {
        tv.setTextColor(Color.parseColor("#b80000"))
    }
    else {
        tv.setTextColor(Color.RED)
    }
}


fun setDescTextDependingOnBMIValue(bmiValue: Double, tv: TextView) {
    if (bmiValue < VERY_UNDERWEIGHT_UPPER_BOUND) {
        tv.setText(R.string.very_underweight_text)
    }
    else if (bmiValue < UNDERWEIGHT_UPPER_BOUND) {
        tv.setText(R.string.underweight_text)
    }
    else if (bmiValue < NORMALWEIGHT_UPPER_BOUND) {
        tv.setText(R.string.normalweight_text)
    }
    else if (bmiValue < OVERWEIGHT_UPPER_BOUND) {
        tv.setText(R.string.overweight_text)
    }
    else {
        tv.setText(R.string.obese_text)
    }
}


fun setBMIShortDescDependingOnBmiValue(bmiValue: Double, tv: TextView) {
    if (bmiValue < VERY_UNDERWEIGHT_UPPER_BOUND) {
        tv.setText(R.string.very_underweight_level)
    }
    else if (bmiValue < UNDERWEIGHT_UPPER_BOUND) {
        tv.setText(R.string.underweight_level)
    }
    else if (bmiValue < NORMALWEIGHT_UPPER_BOUND) {
        tv.setText(R.string.normal_level)
    }
    else if (bmiValue < OVERWEIGHT_UPPER_BOUND) {
        tv.setText(R.string.overweight_level)
    }
    else {
        tv.setText(R.string.obese_level)
    }
}

fun setImageBasedOnBmiValue(bmiValue: Double, im: ImageView) {
    if (bmiValue < VERY_UNDERWEIGHT_UPPER_BOUND) {
        im.setImageResource(R.drawable.very_underweight)
    }
    else if (bmiValue < UNDERWEIGHT_UPPER_BOUND) {
        im.setImageResource(R.drawable.underweight)
    }
    else if (bmiValue < NORMALWEIGHT_UPPER_BOUND) {
        im.setImageResource(R.drawable.normal_weight)
    }
    else if (bmiValue < OVERWEIGHT_UPPER_BOUND) {
        im.setImageResource(R.drawable.overweight)
    }
    else {
        im.setImageResource(R.drawable.obese)
    }
}