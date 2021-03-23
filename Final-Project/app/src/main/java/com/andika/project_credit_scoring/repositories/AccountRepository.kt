package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.RequestAccount
import retrofit2.Response

interface AccountRepository {
    suspend fun getAllAccount() : Account
    suspend fun addAccount(requestAccount: RequestAccount) : Response<Account>
    suspend fun deleteAccount(id:String) : Response<Account>
}