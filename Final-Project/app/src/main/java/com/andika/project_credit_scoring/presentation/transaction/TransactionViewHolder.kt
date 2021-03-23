package com.andika.project_credit_scoring.presentation.transaction

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.CardViewTransactionBinding
import com.andika.project_credit_scoring.entity.ListTransaction
import java.text.NumberFormat
import java.util.*

class TransactionViewHolder(view: View, private val historyClickListener: TransactionClickListener) : RecyclerView.ViewHolder(view) {

    private val binding = CardViewTransactionBinding.bind(view)
    private val localeID = Locale("in", "ID")
    private val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

    fun bind(transaction: ListTransaction?) {
        binding.apply {
            cardTextName.text = transaction?.customer?.name
            cardTextLoan.text = formatRupiah.format(transaction?.loan)
            cardTextIncome.text = formatRupiah.format(transaction?.income)
            cardTextOutcome.text = formatRupiah.format(transaction?.outcome)
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
            cardViewTransaction.setOnClickListener {
                historyClickListener.onDetail(transaction)
            }
        }
    }
}