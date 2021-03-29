package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin
import com.andika.project_credit_scoring.model.roles.ResponseRole

interface LoginRepository {
    suspend fun postLogin(requestLogin: RequestLogin): ResponseLogin
    suspend fun getRole(): ResponseRole
}