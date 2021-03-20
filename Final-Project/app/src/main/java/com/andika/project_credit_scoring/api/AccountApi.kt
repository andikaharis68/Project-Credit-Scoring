package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountApi {
    @GET("account")
    suspend fun getAccount(): Account

    @POST("account")
    suspend fun addAccount(@Body account: Account) : Response<Account>
}