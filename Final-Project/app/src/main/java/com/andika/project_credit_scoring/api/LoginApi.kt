package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin
import com.andika.project_credit_scoring.model.roles.ResponseRole
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun postLogin(@Body requestLogin: RequestLogin) : ResponseLogin

    @GET("role")
    suspend fun getRole() : ResponseRole
}