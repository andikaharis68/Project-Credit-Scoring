package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.RequestAccount
import retrofit2.Response
import retrofit2.http.*

interface AccountApi {
    @GET("users")
    suspend fun getAccount(): Account

    @GET("auth/signup")
    suspend fun addAccount(@Body requestAccount: RequestAccount): Response<Account>

    @DELETE("users/{id}")
    suspend fun deleteAccount(@Path("id") id : String) : Response<Account>
}