package com.andika.project_credit_scoring.repositories

import android.util.Log
import com.andika.project_credit_scoring.api.LoginApi
import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin
import com.andika.project_credit_scoring.model.roles.ResponseRole
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi) : LoginRepository {
    override suspend fun postLogin(requestLogin: RequestLogin): ResponseLogin {
        Log.d("REPO", "$requestLogin")
        return loginApi.postLogin(requestLogin)
    }

    override suspend fun getRole(): ResponseRole {
        return  loginApi.getRole()
    }
}