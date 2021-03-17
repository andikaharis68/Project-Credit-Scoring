package com.andika.project_credit_scoring.presentation.history

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.CardViewHistoryBinding
import com.andika.project_credit_scoring.entity.History

class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CardViewHistoryBinding.bind(view)

    fun bind(history: History) {
        Log.d("DATA","$history")
        binding.apply {
            cardTextName.text = history.approval?.transaction?.customer?.name
            cardTextPrice1.text = "${history.approval?.transaction?.loan} K"
            cardTextKreditRatio.text = history.approval?.transaction?.creditRatio.toString()
            cardTextDate.text = history.approvalDate
            if (history.approval?.transaction?.financeCriteria == true){
                cardTextFinancialCriteria.text = "Pass"
                cardTextFinancialCriteria.setTextColor(Color.parseColor("#00B1B0"))
            } else {
                cardTextFinancialCriteria.text = "Not Pass"
            }
            if (history.approval?.approve == true) {
                cardColorApproval.setBackgroundColor(Color.parseColor("#00B1B0"))
                cardImageApproval.setBackgroundResource(R.drawable.approved)
            } else {
                cardColorApproval.setBackgroundColor(Color.parseColor("#FEC84D"))
                cardImageApproval.setBackgroundResource(R.drawable.rejected)
            }
        }
    }
}