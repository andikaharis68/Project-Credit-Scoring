package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.RequestApproval
import com.andika.project_credit_scoring.entity.Transaction
import com.andika.project_credit_scoring.login.RequestLogin
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TransactionApi {

    @GET("transaction")
    suspend fun getTransaction(): Transaction

    @POST("approval/{id}")
    suspend fun approvalTransaction(@Path("id") id : String, @Body requestApproval: RequestApproval)

}