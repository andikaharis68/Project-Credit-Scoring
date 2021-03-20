package com.andika.project_credit_scoring.presentation.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.entity.Account

class AccountViewAdapter(private val accountClickListener: AccountClickListener) : RecyclerView.Adapter<AccountViewHolder>() {

    var account = ArrayList<Account>()
    var select : String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.card_view_account, parent, false)
        return AccountViewHolder(itemView, accountClickListener)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = account[position]
        if (account.role == select){
            holder.bind(account, select)
        } else if (select == "All") {
            holder.bind(account, select)
        }
    }

    override fun getItemCount(): Int {
        return account.size
    }

    fun setData(data: List<Account>, selected: String) {
        select = selected
        account.clear()
        account.addAll(data)
        notifyDataSetChanged()
    }
}