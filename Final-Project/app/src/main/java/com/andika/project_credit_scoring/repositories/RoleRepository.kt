package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin
import com.andika.project_credit_scoring.model.roles.RequestRole
import com.andika.project_credit_scoring.model.roles.ResponseRole

interface RoleRepository {
    suspend fun getRole(): ResponseRole
    suspend fun addRole(requestRole: RequestRole): ResponseRole
}