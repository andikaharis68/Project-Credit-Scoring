package com.andika.project_credit_scoring.repositories


import com.andika.project_credit_scoring.model.account.RequestAddAccount
import com.andika.project_credit_scoring.model.account.ResponseAccount
import com.andika.project_credit_scoring.model.account.ResponseAddAccount
import com.andika.project_credit_scoring.model.roles.ResponseRole
import retrofit2.Response

interface AccountRepository {
    suspend fun getAllAccount() : ResponseAccount
    suspend fun getAllAccountVerified() : ResponseAccount
    suspend fun getAllAccountNotVerified() : ResponseAccount
    suspend fun addAccount(requestAccount: RequestAddAccount) : ResponseAddAccount
    suspend fun deleteAccount(id:String) : ResponseAccount
    suspend fun getRole() : ResponseRole
}