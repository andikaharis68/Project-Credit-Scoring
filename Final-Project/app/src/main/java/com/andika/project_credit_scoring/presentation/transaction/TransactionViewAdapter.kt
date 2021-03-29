package com.andika.project_credit_scoring.presentation.transaction

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.model.transaction.ListTransaction

class TransactionViewAdapter(private val transactionClickListener: TransactionClickListener) : RecyclerView.Adapter<TransactionViewHolder>() {

    var transaction = ArrayList<ListTransaction?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.card_view_transaction, parent, false)
        return TransactionViewHolder(itemView, transactionClickListener)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transaction[position]
        Log.d("HH", "$transaction")
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transaction.size
    }

    fun setData(data: List<ListTransaction?>) {
        transaction.clear()
        transaction.addAll(data)
        notifyDataSetChanged()
    }

}