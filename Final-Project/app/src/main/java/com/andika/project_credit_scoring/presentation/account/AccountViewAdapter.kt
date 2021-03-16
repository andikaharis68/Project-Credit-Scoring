package com.andika.project_credit_scoring.presentation.account

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.account.Account
import com.andika.project_credit_scoring.account.AccountCoba

class AccountViewAdapter(private val accountClickListener: AccountClickListener) : RecyclerView.Adapter<AccountViewHolder>() {

    var account = ArrayList<AccountCoba>()
    var select : String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.card_view_account, parent, false)
        return AccountViewHolder(itemView, accountClickListener)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = account[position]
        holder.bind(account, select)
    }

    override fun getItemCount(): Int {
        return account.size
    }

    fun setData(data: List<AccountCoba>, selected: String) {
        select = selected
        account.clear()
        account.addAll(data)
        notifyDataSetChanged()
    }
}