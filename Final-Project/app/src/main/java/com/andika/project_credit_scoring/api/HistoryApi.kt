package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.Data
import com.andika.project_credit_scoring.entity.History
import com.andika.project_credit_scoring.entity.ListItem
import retrofit2.Response
import retrofit2.http.GET

interface HistoryApi {
    @GET("report")
    suspend fun getReport() : History
}