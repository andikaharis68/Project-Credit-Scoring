package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.model.transaction.RequestApproval
import com.andika.project_credit_scoring.model.transaction.ResponseApproval
import com.andika.project_credit_scoring.model.transaction.ResponseTransaction

interface TransactionRepository {
    suspend fun getTransaction(): ResponseTransaction
    suspend fun approvalTransaction(id: String, requestApproval: RequestApproval) : ResponseApproval
}