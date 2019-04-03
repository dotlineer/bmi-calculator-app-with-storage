package com.example.bmi.view


//import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.bmi.*
import com.example.bmi.logic.colorTextViewBasedOnScore
import com.example.bmi.logic.setBMIShortDescDependingOnBmiValue
import com.example.bmi.logic.setDescTextDependingOnBMIValue
import com.example.bmi.logic.setImageBasedOnBmiValue
//import com.example.bmiw5.R
import com.example.bmi.R
import java.lang.Double


class LearnMore : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_more)

        val bmiValue = Double.parseDouble(getIntent().extras.getString("BMI_VALUE"))
        val toolbar: android.support.v7.widget.Toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        colorTextViewBasedOnScore(
            bmiValue,
            findViewById<TextView>(R.id.tv_lm_1)
        )
        colorTextViewBasedOnScore(
            bmiValue,
            findViewById<TextView>(R.id.lm_bmi_desc)
        )
        setImageBasedOnBmiValue(
            bmiValue,
            findViewById<ImageView>(R.id.img_lm)
        )

        setBMIShortDescDependingOnBmiValue(
            bmiValue,
            findViewById<TextView>(R.id.lm_bmi_desc)
        )

        (findViewById(R.id.tv_lm_1) as TextView).setText(getIntent().extras.getString("BMI_VALUE"))
        colorTextViewBasedOnScore(
            bmiValue,
            (findViewById(R.id.tv_lm_1) as TextView)
        )
        setDescTextDependingOnBMIValue(
            bmiValue,
            (findViewById(R.id.tv_lm_2) as TextView)
        )

    }
}
