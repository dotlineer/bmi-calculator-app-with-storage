package com.example.bmi.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.bmi.R
import com.example.bmi.logic.HistoryItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class History : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val resType: Type = object : TypeToken<ArrayList<HistoryItem>>() {}.type
        val lastResults: ArrayList<HistoryItem> = Gson().fromJson(intent.getStringExtra("lastResultsJson"), resType) as ArrayList<HistoryItem>

        val recyclerView = findViewById(R.id.historyRecyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = HistoryAdapter(lastResults)
        recyclerView.adapter = adapter
    }
}
