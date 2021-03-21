package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.RequestApproval
import com.andika.project_credit_scoring.entity.Transaction
import com.andika.project_credit_scoring.login.RequestLogin
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TransactionApi {

    @GET("transaction")
    suspend fun getTransaction(): Transaction

    @POST("approval")
    suspend fun approvalTransaction(@Body requestApproval: RequestApproval)

}