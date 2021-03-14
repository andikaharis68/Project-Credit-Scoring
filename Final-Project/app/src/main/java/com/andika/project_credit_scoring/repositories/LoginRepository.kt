package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin

interface LoginRepository {
    suspend fun postLogin(requestLogin: RequestLogin): ResponseLogin
}