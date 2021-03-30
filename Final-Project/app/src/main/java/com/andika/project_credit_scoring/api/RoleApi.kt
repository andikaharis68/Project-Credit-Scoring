package com.andika.project_credit_scoring.api

import com.andika.project_credit_scoring.model.roles.RequestRole
import com.andika.project_credit_scoring.model.roles.ResponseRole
import retrofit2.http.*

interface RoleApi {

    @GET("role")
    suspend fun getRole() : ResponseRole

    @POST("role")
    suspend fun addRole(@Body requestRole: RequestRole) : ResponseRole

    @DELETE("role/{id}")
    suspend fun deleteRole(@Path("id") id : String) : ResponseRole

}