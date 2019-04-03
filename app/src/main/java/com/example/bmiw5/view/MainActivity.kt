package com.example.bmi.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.text.DecimalFormat
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import com.example.bmi.R
import com.example.bmi.logic.*
import java.time.LocalDateTime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    var unitsMode: String = "european"
    var menuToolbar: Menu? = null
    var learnMoreBtnEnabled = false
    var bmiRes: Double = 0.0
    val capacity = 10
    var lastResults: StackWithCapacity<HistoryItem> = StackWithCapacity(capacity)

    fun changeUnitsToEuropean() {
        unitsMode = "european"
        menuToolbar?.findItem(R.id.mmi_american)?.setChecked(false)
        menuToolbar?.findItem(R.id.mmi_european)?.setChecked(true)

        (findViewById(R.id.tvMassText) as TextView).setText(R.string.inputWeightEU)
        (findViewById(R.id.tvHeightText) as TextView).setText(R.string.inputHeightEU)
    }

    fun changeUnitsToAmerican() {
        unitsMode = "american"
        menuToolbar?.findItem(R.id.mmi_european)?.setChecked(false)
        menuToolbar?.findItem(R.id.mmi_american)?.setChecked(true)

        (findViewById(R.id.tvMassText) as TextView).setText(R.string.inputWeightUS)
        (findViewById(R.id.tvHeightText) as TextView).setText(R.string.inputHeightUS)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (findViewById(R.id.btn_learn_more) as Button).isEnabled = false
        learnMoreBtnEnabled = false

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }
        else {
            changeUnitsToEuropean()
        }

        loadData()

        val toolbar: android.support.v7.widget.Toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar)

        var strMass: String
        var strHeight: String
        val button = findViewById(R.id.btn_calculate) as Button
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                strMass = (findViewById(R.id.et_mass) as EditText).getText().toString()
                strHeight = (findViewById(R.id.et_height) as EditText).getText().toString()

                try {
                    var iMass: Int = java.lang.Integer.parseInt(strMass)
                    var iHeight: Int = java.lang.Integer.parseInt(strHeight)

                    (findViewById(R.id.tvBmiResult) as TextView).setTextColor(Color.parseColor("#7289da"))

                    bmiRes = when (unitsMode) {
                        "european" -> BmiForKgM(iMass, iHeight).countBmi()
                        "american" -> BmiForLbsInches(iMass, iHeight).countBmi()
                        else -> throw Exception("Implementation error")
                    }

                    (findViewById(R.id.btn_learn_more) as Button).isEnabled = true
                    learnMoreBtnEnabled = true

                    val newFormat = DecimalFormat("#.##")
                    val bmiResRounded: Double = java.lang.Double.valueOf(newFormat.format(bmiRes))

                    val splitDate = LocalDateTime.now().toString().toString().split("T")
                    val datePart = splitDate[0]
                    val hourMinPart = splitDate[1].substring(0, 5)
                    val calculatedBmiResult = HistoryItem(iMass, iHeight, unitsMode, bmiResRounded, datePart + "\n" + hourMinPart)

                    lastResults.push(calculatedBmiResult)
                    saveData()

                    colorTextViewBasedOnScore(
                        bmiRes,
                        (findViewById(R.id.tvBmiResult) as TextView)
                    )

                    (findViewById(R.id.tvBmiResult) as TextView).setText(bmiResRounded.toString())
                    setBMIShortDescDependingOnBmiValue(
                        bmiRes,
                        findViewById<TextView>(R.id.tvBmiLevel)
                    )
                    colorTextViewBasedOnScore(
                        bmiRes,
                        findViewById<TextView>(R.id.tvBmiLevel)
                    )
                }
                catch (e: Exception) {
                    println(e.message)
                    println(e.stackTrace)
                    e.printStackTrace()
                    (findViewById(R.id.tvBmiResult) as TextView).setTextColor(Color.parseColor("#fe5f55"))
                    (findViewById(R.id.tvBmiResult) as TextView).setText("Arguments out of range")
                    (findViewById(R.id.tvBmiLevel) as TextView).setText("")
                    learnMoreBtnEnabled = false
                    (findViewById(R.id.btn_learn_more) as Button).isEnabled = false
                }
            }
        })

        val btnLearnMore: Button = findViewById(R.id.btn_learn_more)
        btnLearnMore.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, LearnMore::class.java).apply {
                    val valuePutAsStr = (findViewById(R.id.tvBmiResult) as TextView).text
                    putExtra("BMI_VALUE", valuePutAsStr)
                }
                startActivity(intent)
            }
        })
    }


        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dots_menu, menu)
        menuToolbar = menu

        when (unitsMode) {
            "european" -> changeUnitsToEuropean()
            "american" -> changeUnitsToAmerican()
            else -> throw Exception("Implementation error")
        }

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_change_units -> {
            }
            R.id.mi_about -> {
                val intent = Intent(this, AboutInfo::class.java)
                startActivity(intent)
            }
            R.id.mmi_european -> {
                changeUnitsToEuropean()
            }
            R.id.mmi_american -> {
                changeUnitsToAmerican()
            }
            R.id.mi_last_results -> {
                val intent = Intent(this@MainActivity, History::class.java)
                intent.putExtra("lastResultsJson", (Gson().toJson(lastResults.toArrayList()))  )
                startActivity(intent)
            }
            else -> { }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("bmiValue", findViewById<TextView>(R.id.tvBmiResult).text.toString())
        outState?.putDouble("bmiRes", bmiRes)
        outState?.putString("bmiLevel", findViewById<TextView>(R.id.tvBmiLevel).text.toString())
        outState?.putString("massInputByUser", findViewById<TextView>(R.id.tvMassText).text.toString())
        outState?.putString("heightInputByUser", findViewById<TextView>(R.id.tvHeightText).text.toString())
        outState?.putBoolean("learnMoreBtnEnabled", learnMoreBtnEnabled)
        outState?.putString("unitsMode", unitsMode)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        findViewById<TextView>(R.id.tvBmiResult).setText(savedInstanceState?.getString("bmiValue").toString())
        findViewById<TextView>(R.id.tvBmiLevel).setText(savedInstanceState?.getString("bmiLevel").toString())
        findViewById<TextView>(R.id.tvMassText).setText(savedInstanceState?.getString("massInputByUser").toString())
        findViewById<TextView>(R.id.tvHeightText).setText(savedInstanceState?.getString("heightInputByUser").toString())

        bmiRes = savedInstanceState?.getDouble("bmiRes") as Double
        colorTextViewBasedOnScore(bmiRes, (findViewById(R.id.tvBmiResult) as TextView))
        colorTextViewBasedOnScore(bmiRes, findViewById<TextView>(R.id.tvBmiLevel))

        learnMoreBtnEnabled = savedInstanceState?.getBoolean("learnMoreBtnEnabled")
        if (learnMoreBtnEnabled) {
            (findViewById(R.id.btn_learn_more) as Button).isEnabled = true
        }
        else {
            (findViewById(R.id.btn_learn_more) as Button).isEnabled = false
        }

        unitsMode = savedInstanceState?.getString("unitsMode")
        when (unitsMode) {
            "european" -> changeUnitsToEuropean()
            "american" -> changeUnitsToAmerican()
            else -> throw Exception("Implementation error")
        }
    }

    fun saveData() {
        val editor: SharedPreferences.Editor  = getPreferences(Context.MODE_PRIVATE).edit()
        try {
            val gson = Gson()
            val json = gson.toJson(lastResults)
            editor.putString("lastResultsAsStack", json)
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadData() {
        val sharedPrefs = getPreferences(Context.MODE_PRIVATE)
        if (sharedPrefs != null) {
            val json: String? = sharedPrefs.getString("lastResultsAsStack", null)
            if (json != null) {
                try {
                    val gson = Gson()
                    val resType: Type = object : TypeToken<StackWithCapacity<HistoryItem>>() {}.type
                    lastResults = gson.fromJson(json, resType) as StackWithCapacity<HistoryItem>
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


}