package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.RequestAccount
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountApi {
    @GET("users")
    suspend fun getAccount(): Account

    @GET("auth/signup")
    suspend fun addAccount(@Body requestAccount: RequestAccount): Response<Account>
}