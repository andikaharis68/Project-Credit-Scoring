package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.model.roles.RequestRole
import com.andika.project_credit_scoring.model.roles.ResponseRole
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RoleApi {

    @GET("role")
    suspend fun getRole() : ResponseRole

    @POST("role")
    suspend fun addRole(@Body requestRole: RequestRole) : ResponseRole
}