package com.example.bmi.logic


class HistoryItem(val mass: Int, val height: Int, val units: String, val bmiResult: Double, val date: String) {

    override fun toString(): String {
        var strBuf: StringBuffer = StringBuffer()
        strBuf.append("Mass: ").append(mass).append("; Height: ").append(height).append("; Units: ").append(units).append("; bmiResult: ").append(bmiResult).append("; Date: ").append(date)
        return strBuf.toString()
    }

}