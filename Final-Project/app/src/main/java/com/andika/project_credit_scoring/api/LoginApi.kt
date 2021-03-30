package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin
import com.andika.project_credit_scoring.model.roles.ResponseRole
import com.andika.project_credit_scoring.model.user.RequestPassword
import com.andika.project_credit_scoring.model.user.RequestUser
import com.andika.project_credit_scoring.model.user.ResponsePassword
import com.andika.project_credit_scoring.model.user.ResponseUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun postLogin(@Body requestLogin: RequestLogin) : ResponseLogin

    @GET("role")
    suspend fun getRole() : ResponseRole

    @GET("users")
    suspend fun getUser() : ResponseUser

    @PATCH("users")
    suspend fun editUser(@Body requestUser: RequestUser) : ResponseUser

    @PATCH("users/password")
    suspend fun editPassword(@Body requestPassword: RequestPassword) : ResponsePassword
}