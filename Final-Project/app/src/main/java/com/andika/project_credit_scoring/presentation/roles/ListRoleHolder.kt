package com.andika.project_credit_scoring.presentation.roles

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.databinding.CardViewRoleBinding
import com.andika.project_credit_scoring.model.roles.ListRole

class ListRoleHolder(view: View, private val roleListClickListener: RoleListClickListener) : RecyclerView.ViewHolder(view) {

    private val binding = CardViewRoleBinding.bind(view)

    fun bind(role: ListRole?) {
        binding.apply {
            cardNameRole.text = role?.name
            if (role?.inputCustomer!!){
                checkInputCustomer.visibility  = View.VISIBLE
            } else {
                checkInputCustomer.visibility  = View.GONE
            }
            if (role?.readAllCustomer!!){
                checkReadCustomer.visibility  = View.VISIBLE
            } else {
                checkReadCustomer.visibility  = View.GONE
            }
            if (role?.inputTransaction!!){
                checkInputTransaction.visibility  = View.VISIBLE
            } else {
                checkInputTransaction.visibility  = View.GONE
            }
            if (role?.readAllTransaction!!){
                checkReadTransaction.visibility  = View.VISIBLE
            } else {
                checkReadTransaction.visibility  = View.GONE
            }
            if (role?.approveTransaction!!){
                checkApproveTransaction.visibility  = View.VISIBLE
            } else {
                checkApproveTransaction.visibility  = View.GONE
            }
            if (role?.readAllReport!!){
                checkReadAllReport.visibility  = View.VISIBLE
            } else {
                checkReadAllReport.visibility  = View.GONE
            }
            if (role?.readAllReportByTransaction!!){
                checkReadReportTransaction.visibility  = View.VISIBLE
            } else {
                checkReadReportTransaction.visibility  = View.GONE
            }

            cardBtnRoleDelete.setOnClickListener {
                roleListClickListener.onDelete(role?.id!!)
            }
        }
    }
}