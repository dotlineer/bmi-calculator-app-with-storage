package com.example.bmi.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.bmi.R


class AboutInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_info)

        val btnClosing: Button = findViewById(R.id.button)
        btnClosing.setOnClickListener(View.OnClickListener {
            finish()
        })

    }

}
