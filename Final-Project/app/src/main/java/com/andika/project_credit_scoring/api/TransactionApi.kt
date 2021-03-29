package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.model.transaction.RequestApproval
import com.andika.project_credit_scoring.model.transaction.ResponseApproval
import com.andika.project_credit_scoring.model.transaction.ResponseTransaction
import retrofit2.http.*

interface TransactionApi {

    @GET("approval/waiting")
    suspend fun getTransaction(): ResponseTransaction

    @PATCH("approval/{id}")
    suspend fun approvalTransaction(@Path("id") id : String, @Body requestApproval: RequestApproval) : ResponseApproval

}