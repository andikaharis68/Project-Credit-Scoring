package com.andika.project_credit_scoring.repositories

import android.util.Log
import com.andika.project_credit_scoring.api.HistoryApi
import com.andika.project_credit_scoring.model.history.ResponseHistory
import com.andika.project_credit_scoring.model.history.ResponseTotal
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(private val historyApi: HistoryApi): HistoryRepository{
    override suspend fun getAllHistory() : ResponseHistory {
        return historyApi.getReport()
    }

    override suspend fun getRejectedHistory(): ResponseHistory {
        return historyApi.getRejectedReport()
    }

    override suspend fun getApprovedHistory(): ResponseHistory {
        return historyApi.getApprovedReport()
    }

    override suspend fun getTotal(): ResponseTotal {
        return historyApi.getTotal()
    }

}