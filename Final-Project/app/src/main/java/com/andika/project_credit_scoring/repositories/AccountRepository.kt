package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.entity.Account
import retrofit2.Response


interface AccountRepository {
    suspend fun getAccount() : Response<List<Account>>
    fun addAccount(account: Account)
}