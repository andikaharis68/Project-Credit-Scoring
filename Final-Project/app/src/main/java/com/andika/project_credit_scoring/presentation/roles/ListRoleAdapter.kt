package com.andika.project_credit_scoring.presentation.roles

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.model.history.ListHistory
import com.andika.project_credit_scoring.model.roles.ListRole
import com.andika.project_credit_scoring.presentation.history.HistoryClickListener

class ListRoleAdapter(private val roleListClickListener: RoleListClickListener) : RecyclerView.Adapter<ListRoleHolder>() {

    var roles = ArrayList<ListRole?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRoleHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.card_view_role, parent, false)
        return ListRoleHolder(itemView, roleListClickListener)
    }

    override fun onBindViewHolder(holder: ListRoleHolder, position: Int) {
        val role = roles[position]
        holder.bind(role)
    }

    override fun getItemCount(): Int {
        return roles.size
    }

    fun setData(data: List<ListRole?>) {
        roles.clear()
        roles.addAll(data)
        notifyDataSetChanged()
    }
}