package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.entity.Account
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountApi {
    @GET("users")
    suspend fun getAccount(): Account

    @GET("users/activate/{id}")
    suspend fun activateAccount(@Path("id") id : String): Response<Account>
}