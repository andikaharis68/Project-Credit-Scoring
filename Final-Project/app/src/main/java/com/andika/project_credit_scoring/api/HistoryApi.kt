package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.model.history.ResponseHistory
import retrofit2.http.GET

interface HistoryApi {
    @GET("report")
    suspend fun getReport() : ResponseHistory

    @GET("approval/approved")
    suspend fun getApprovedReport() : ResponseHistory

    @GET("approval/rejected")
    suspend fun getRejectedReport() : ResponseHistory
}