package com.andika.project_credit_scoring.presentation.history

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.CardViewHistoryBinding
import com.andika.project_credit_scoring.entity.History
import com.andika.project_credit_scoring.entity.ListHistory
import java.text.NumberFormat
import java.util.*

class HistoryViewHolder(view: View, private val historyClickListener: HistoryClickListener) : RecyclerView.ViewHolder(view) {

    private val binding = CardViewHistoryBinding.bind(view)
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

    fun bind(history: ListHistory?) {
        binding.apply {
            cardTextName.text = history?.approval?.transaction?.customer?.name
            cardTextPrice1.text = formatRupiah.format(history?.approval?.transaction?.loan)
            cardTextKreditRatio.text = "${history?.approval?.transaction?.creditRatio} %"
            cardTextDate.text = history?.approvalDate
            cardTextStaff.text = history?.approval?.transaction?.submitter
            if (history?.approval?.transaction?.financeCriteria == true){
                cardTextFinancialCriteria.text = "Pass"
                cardTextFinancialCriteria.setTextColor(Color.parseColor("#00B1B0"))
            } else {
                cardTextFinancialCriteria.text = "Not Pass"
                cardTextFinancialCriteria.setTextColor(Color.parseColor("#ba0f30"))
            }
            if (history?.approval?.approve == true) {
                cardColorApproval.setBackgroundColor(Color.parseColor("#00B1B0"))
                cardImageApproval.setBackgroundResource(R.drawable.approved)
            } else {
                cardColorApproval.setBackgroundColor(Color.parseColor("#FEC84D"))
                cardImageApproval.setBackgroundResource(R.drawable.rejected)
            }
            layoutCardViewHistory.setOnClickListener {
                historyClickListener.onDetail(history)
            }
        }
    }
}

