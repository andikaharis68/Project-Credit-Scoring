package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.model.history.ResponseHistory


interface HistoryRepository {
    suspend fun getAllHistory() : ResponseHistory
    suspend fun getRejectedHistory() : ResponseHistory
    suspend fun getApprovedHistory() : ResponseHistory
}