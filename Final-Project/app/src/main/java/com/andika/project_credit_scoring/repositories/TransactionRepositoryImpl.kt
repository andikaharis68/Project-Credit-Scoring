package com.andika.project_credit_scoring.repositories

import android.util.Log
import com.andika.project_credit_scoring.api.TransactionApi
import com.andika.project_credit_scoring.model.account.ResponseAccount
import com.andika.project_credit_scoring.model.transaction.RequestApproval
import com.andika.project_credit_scoring.model.transaction.ResponseApproval
import com.andika.project_credit_scoring.model.transaction.ResponseTransaction
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionApi: TransactionApi) :
    TransactionRepository {
    override suspend fun getTransaction(): ResponseTransaction {
        return transactionApi.getTransaction()
        Log.d("DATA", "${transactionApi.getTransaction()}")
    }

    override suspend fun approvalTransaction(id: String, requestApproval: RequestApproval): ResponseApproval {
        return transactionApi.approvalTransaction(id, requestApproval)
        Log.d("APPROVAL REPO", "$id --- $requestApproval")
    }

}