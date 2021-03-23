package com.andika.project_credit_scoring.presentation.transaction

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.CardViewTransactionBinding
import com.andika.project_credit_scoring.entity.ListTransaction
import com.andika.project_credit_scoring.entity.RequestApproval

class TransactionViewHolder(view: View, private val historyClickListener: TransactionClickListener) : RecyclerView.ViewHolder(view) {

    private val binding = CardViewTransactionBinding.bind(view)
    private var statusNow = ""

    fun bind(transaction: ListTransaction?, status : String) {
        statusNow = status
        binding.apply {
            cardTextName.text = transaction?.customer?.name
            cardTextLoan.text = "Rp. ${transaction?.loan}"
            cardTextIncome.text = "Rp. ${transaction?.income}"
            cardTextOutcome.text = "Rp. ${transaction?.outcome}"
            cardTextRatio.text = "${transaction?.creditRatio} %"
            if (transaction?.employeeCriteria == true) {
                cardTextEmployeeCriteria.text = "Pass"
                cardTextEmployeeCriteria.setTextColor(Color.parseColor("#00B1B0"))
            } else {
                cardTextEmployeeCriteria.text = "Not Pass"
                cardTextEmployeeCriteria.setTextColor(Color.parseColor("#ba0f30"))
            }
            if (transaction?.financeCriteria == true) {
                cardTextFinancialCriteria.text = "Pass"
                cardTextFinancialCriteria.setTextColor(Color.parseColor("#00B1B0"))
            } else {
                cardTextFinancialCriteria.text = "Not Pass"
                cardTextFinancialCriteria.setTextColor(Color.parseColor("#ba0f30"))
            }
            if (statusNow == ""){
                cardViewTransaction.setOnClickListener {
                    historyClickListener.onDetail(transaction)
                }
                cardImageStatus.visibility = View.GONE
                cardLayTransaction.setBackgroundResource(R.drawable.round_corner_white)
            } else if (statusNow == "APPROVE") {
                cardImageStatus.visibility = View.VISIBLE
                cardImageStatus.setBackgroundResource(R.drawable.approved)
                cardLayTransaction.setBackgroundResource(R.drawable.round_corner_white)
            } else if (statusNow == "REJECT") {
                cardImageStatus.visibility = View.VISIBLE
                cardLayTransaction.setBackgroundResource(R.drawable.rejected)
            }
        }
    }
}