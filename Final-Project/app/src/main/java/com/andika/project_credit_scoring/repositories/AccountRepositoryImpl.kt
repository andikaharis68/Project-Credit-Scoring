package com.andika.project_credit_scoring.repositories

import android.util.Log
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.api.AccountApi
import com.andika.project_credit_scoring.entity.History
import retrofit2.Response
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val accountApi: AccountApi) : AccountRepository {

    override suspend fun getAllAccount() : Account {
        return accountApi.getAccount()
        Log.d("DATA", "${accountApi.getAccount()}")
    }

    override suspend fun activateAccount(id : String) : Response<Account> {
        return accountApi.activateAccount(id)
    }
}