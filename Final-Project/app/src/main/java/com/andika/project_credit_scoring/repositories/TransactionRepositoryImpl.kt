package com.andika.project_credit_scoring.repositories

import android.util.Log
import com.andika.project_credit_scoring.api.TransactionApi
import com.andika.project_credit_scoring.entity.Transaction
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionApi: TransactionApi) : TransactionRepository {
    override suspend fun getTransaction(): Transaction {
        return transactionApi.getTransaction()
        Log.d("DATA", "${transactionApi.getTransaction()}")
    }

    override suspend fun approvalTransaction(requestApproval: RequestApproval) {
        transactionApi.approvalTransaction(requestApproval)
    }

}