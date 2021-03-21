package com.andika.project_credit_scoring.presentation.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.ListAccount

class AccountViewAdapter (private val accountClickListener: AccountClickListener) : RecyclerView.Adapter<AccountViewHolder>() {

    var accounts = ArrayList<ListAccount?>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.card_view_account, parent, false)
        return AccountViewHolder(itemView, accountClickListener)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = accounts[position]
        holder.bind(account)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    fun setData(data: List<ListAccount?>) {
        accounts.clear()
        accounts.addAll(data)
        notifyDataSetChanged()
    }
}