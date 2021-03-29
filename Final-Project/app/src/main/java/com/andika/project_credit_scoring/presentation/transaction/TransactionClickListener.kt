package com.andika.project_credit_scoring.presentation.transaction

import com.andika.project_credit_scoring.model.transaction.ListTransaction

interface TransactionClickListener {
    fun onDetail(transaction: ListTransaction?)
}