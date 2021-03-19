package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.entity.History
import retrofit2.Response

interface HistoryRepository {
    suspend fun getAllHistory() : History
}