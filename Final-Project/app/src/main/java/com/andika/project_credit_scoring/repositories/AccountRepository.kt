package com.andika.project_credit_scoring.repositories


import com.andika.project_credit_scoring.model.account.RequestAddAccount
import com.andika.project_credit_scoring.model.account.ResponseAccount
import retrofit2.Response

interface AccountRepository {
    suspend fun getAllAccount() : ResponseAccount
    suspend fun addAccount(requestAccount: RequestAddAccount) : ResponseAccount
    suspend fun deleteAccount(id:String) : ResponseAccount
}