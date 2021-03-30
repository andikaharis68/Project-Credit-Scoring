package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin
import com.andika.project_credit_scoring.model.roles.ResponseRole
import com.andika.project_credit_scoring.model.user.RequestPassword
import com.andika.project_credit_scoring.model.user.RequestUser
import com.andika.project_credit_scoring.model.user.ResponsePassword
import com.andika.project_credit_scoring.model.user.ResponseUser

interface LoginRepository {
    suspend fun postLogin(requestLogin: RequestLogin): ResponseLogin
    suspend fun getRole(): ResponseRole
    suspend fun getUser(): ResponseUser
    suspend fun editUser(requestUser: RequestUser): ResponseUser
    suspend fun editPassword(requestPassword: RequestPassword): ResponsePassword
}