package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.model.history.ResponseHistory
import com.andika.project_credit_scoring.model.history.ResponseTotal


interface HistoryRepository {
    suspend fun getAllHistory() : ResponseHistory
    suspend fun getRejectedHistory() : ResponseHistory
    suspend fun getApprovedHistory() : ResponseHistory
    suspend fun getTotal() : ResponseTotal
}