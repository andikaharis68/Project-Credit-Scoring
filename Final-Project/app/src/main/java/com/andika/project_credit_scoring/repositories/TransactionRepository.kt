package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.entity.RequestApproval
import com.andika.project_credit_scoring.entity.Transaction

interface TransactionRepository {
    suspend fun getTransaction() : Transaction
    suspend fun approvalTransaction(requestApproval: RequestApproval)
}