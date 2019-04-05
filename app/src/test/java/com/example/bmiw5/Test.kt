package com.example.bmiw5


import com.example.bmi.logic.BmiForKgM
import com.example.bmi.logic.BmiForLbsInches
import com.example.bmi.logic.NORMALWEIGHT_UPPER_BOUND
import com.example.bmi.logic.UNDERWEIGHT_UPPER_BOUND
import junit.framework.TestCase.fail
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException


class Test {

    @Test
    fun testValidBmiData () {
        val bmi = BmiForKgM(65, 170)
        Assert.assertEquals(22.491, bmi.countBmi(), 0.001) // delta - przymowac o rzad wiecej niz ten rzad, ktorego planujemy wykorzystac w programie
    }

    @Test
    fun testBmiBounds() {
        val bmi: Double = BmiForKgM(73, 192).countBmi()
        Assert.assertTrue( (UNDERWEIGHT_UPPER_BOUND < bmi) && (bmi < NORMALWEIGHT_UPPER_BOUND) )
    }


    @Test
    fun testArgumentsOutOfRange() {
        try {
            val bmi: Double = BmiForKgM(73, 4).countBmi()
        }
        catch (e: IllegalArgumentException) { }
        catch (e: Exception) {
            fail("IllegalArgumentException should be thrown");
        }
    }

    @Test
    fun testDivisionByZero() {
        try {
            val bmi: Double = BmiForKgM(63, 0).countBmi()
        }
        catch (e: IllegalArgumentException) { }
        catch (e: Exception) {
            fail("IllegalArgumentException should be thrown");
        }
    }

    @Test
    fun testProperConversionBetweenEuropeanAndAmericanUnits() {
        val bmiEuropean = BmiForKgM(71, 190).countBmi() // 19.664
        val bmiAmerican = BmiForLbsInches(157, 75).countBmi() // 19.620
        Assert.assertEquals(0.044, bmiEuropean - bmiAmerican, 0.01)
    }

    @Test
    fun testEdgeValuesOfInput() {
        val bmiEuropean = BmiForKgM(40, 300).countBmi()
    }

}