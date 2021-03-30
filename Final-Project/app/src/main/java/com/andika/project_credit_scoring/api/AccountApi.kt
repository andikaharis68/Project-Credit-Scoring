package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.model.account.RequestAddAccount
import com.andika.project_credit_scoring.model.account.ResponseAccount
import com.andika.project_credit_scoring.model.roles.ResponseRole
import retrofit2.Response
import retrofit2.http.*

interface AccountApi {
    @GET("master")
    suspend fun getAccount(): ResponseAccount

    @GET("master/verified")
    suspend fun getAccountVerified(): ResponseAccount

    @GET("master/notverified")
    suspend fun getAccountNotVerified(): ResponseAccount

    @POST("master/signup")
    suspend fun addAccount(@Body requestAddAccount: RequestAddAccount): ResponseAccount

    @DELETE("master/{id}")
    suspend fun deleteAccount(@Path("id") id : String) : ResponseAccount

    @GET("role")
    suspend fun getRole() : ResponseRole
}