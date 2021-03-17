package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.entity.Account
import retrofit2.Response
import retrofit2.http.GET

interface AccountApi {
    @GET("/account")
    suspend fun getAccount(): Response<List<Account>>
}