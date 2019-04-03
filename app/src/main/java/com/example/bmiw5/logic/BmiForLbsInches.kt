package com.example.bmi.logic

import logic.Bmi


class BmiForLbsInches(var mass: Int, var height: Int) : Bmi {

    override fun countBmi() : Double {
        require(mass >= 88 && mass <= 1102 && height >= 19 && height <= 119) { "Invalid data" }
        val bmi : Double = 10000.0 * (mass * 0.4535924) / (height * 2.54 * height * 2.54)
        return bmi
    }


}
