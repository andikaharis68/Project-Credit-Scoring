package com.andika.project_credit_scoring.presentation.transaction

import com.andika.project_credit_scoring.entity.ListTransaction
import com.andika.project_credit_scoring.entity.RequestApproval
import com.andika.project_credit_scoring.entity.Transaction

interface TransactionClickListener {
    fun onDetail(transaction: ListTransaction?)
}