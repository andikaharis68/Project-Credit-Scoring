package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/signin")
    suspend fun postLogin(@Body requestLogin: RequestLogin) : ResponseLogin
}