package com.andika.project_credit_scoring.repositories

import android.util.Log

import com.andika.project_credit_scoring.api.AccountApi
import com.andika.project_credit_scoring.model.account.RequestAddAccount
import com.andika.project_credit_scoring.model.account.ResponseAccount
import com.andika.project_credit_scoring.model.roles.ResponseRole
import retrofit2.Response
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val accountApi: AccountApi) : AccountRepository {

    override suspend fun getAllAccount() : ResponseAccount {
        return accountApi.getAccount()
        Log.d("DATA", "${accountApi.getAccount()}")
    }

    override suspend fun getAllAccountVerified(): ResponseAccount {
        return accountApi.getAccountVerified()
    }

    override suspend fun getAllAccountNotVerified(): ResponseAccount {
        return accountApi.getAccountNotVerified()
    }

    override suspend fun addAccount(requestAccount: RequestAddAccount): ResponseAccount {
        return accountApi.addAccount(requestAccount)
    }

    override suspend fun deleteAccount(id: String): ResponseAccount {
        return accountApi.deleteAccount(id)
    }

    override suspend fun getRole(): ResponseRole {
        return accountApi.getRole()
    }
}