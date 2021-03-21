package com.andika.project_credit_scoring.repositories

import android.util.Log
import com.andika.project_credit_scoring.api.HistoryApi
import com.andika.project_credit_scoring.entity.History
import retrofit2.Response
import javax.inject.Inject

//@Inject constructor(historyApi: HistoryApi)

class HistoryRepositoryImpl @Inject constructor(private val historyApi: HistoryApi): HistoryRepository{
    override suspend fun getAllHistory() : History {
        return historyApi.getReport()
        Log.d("DATA", "${historyApi.getReport()}")
    }

}