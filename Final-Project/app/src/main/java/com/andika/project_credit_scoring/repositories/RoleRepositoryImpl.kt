package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.api.LoginApi
import com.andika.project_credit_scoring.api.RoleApi
import com.andika.project_credit_scoring.model.roles.RequestRole
import com.andika.project_credit_scoring.model.roles.ResponseRole
import javax.inject.Inject

class RoleRepositoryImpl @Inject constructor(private val roleApi: RoleApi) : RoleRepository {
    override suspend fun getRole(): ResponseRole {
        return roleApi.getRole()
    }

    override suspend fun deleteRole(id: String): ResponseRole {
        return roleApi.deleteRole(id)
    }

    override suspend fun addRole(requestRole: RequestRole): ResponseRole {
        return roleApi.addRole(requestRole)
    }
}