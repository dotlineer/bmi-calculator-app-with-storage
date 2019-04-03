package com.example.bmi.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bmi.logic.HistoryItem
import com.example.bmi.R


class HistoryAdapter(private val mDataList: ArrayList<HistoryItem>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.tvBmiResult.text = mDataList[position].bmiResult.toString()
        holder.tvMass.text = mDataList[position].mass.toString()
        holder.tvHeight.text = mDataList[position].height.toString()
        holder.tvDate.text = mDataList[position].date.toString()
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvBmiResult: TextView = itemView.findViewById<View>(R.id.tv_hist_bmi_value) as TextView
        internal var tvMass: TextView = itemView.findViewById<View>(R.id.tv_hist_mass_value) as TextView
        internal var tvHeight: TextView = itemView.findViewById<View>(R.id.tv_hist_height_value) as TextView
        internal var tvDate: TextView = itemView.findViewById<View>(R.id.tv_hist_date_value) as TextView
    }

}