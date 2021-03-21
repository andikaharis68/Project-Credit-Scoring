package com.andika.project_credit_scoring.presentation.history

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.entity.ListHistory

class HistoryViewAdapter(private val historyClickListener: HistoryClickListener) : RecyclerView.Adapter<HistoryViewHolder>() {

    var history = ArrayList<ListHistory?>()
    var select : String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.card_view_history, parent, false)
        return HistoryViewHolder(itemView, historyClickListener)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val account = history[position]
        Log.d("HH", "$account")
        holder.bind(account)
    }

    override fun getItemCount(): Int {
        return history.size
    }

    fun setData(data: List<ListHistory?>) {
        history.clear()
        history.addAll(data)
        notifyDataSetChanged()
    }

}