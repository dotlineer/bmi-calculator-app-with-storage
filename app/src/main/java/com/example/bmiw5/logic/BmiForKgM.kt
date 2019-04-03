package com.example.bmi.logic

import logic.Bmi


class BmiForKgM(var mass: Int, var height: Int) : Bmi {

    override fun countBmi() : Double {
        require(mass >= 40 && mass <= 500 && height >= 50 && height <= 300) { "Invalid data" }
        val bmi : Double = 10000.0 * mass / (height * height)
        return bmi
    }

}
