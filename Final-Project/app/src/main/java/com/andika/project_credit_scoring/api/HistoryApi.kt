package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.model.history.ResponseHistory
import com.andika.project_credit_scoring.model.history.ResponseTotal
import retrofit2.http.GET

interface HistoryApi {
    @GET("report")
    suspend fun getReport() : ResponseHistory

    @GET("report/approved")
    suspend fun getApprovedReport() : ResponseHistory

    @GET("report/rejected")
    suspend fun getRejectedReport() : ResponseHistory

    @GET("total")
    suspend fun getTotal() : ResponseTotal

}