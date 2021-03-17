package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.api.AccountApi
import retrofit2.Response
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val accountApi: AccountApi) : AccountRepository {


    override suspend fun getAccount(): Response<List<Account>> {
        return accountApi.getAccount()
    }

    override fun addAccount(account: Account) {
        accountList.add(account)
    }

    companion object {
        var accountList = arrayListOf(
            Account(
                "andika",
                "andika@gmail.com",
                "andika123",
                "supervisor"
            ),
            Account(
                "gilang",
                "gilang@gmail.com",
                "gilang123",
                "staff"
            ),
            Account(
                "gilang",
                "gilang@gmail.com",
                "gilang123",
                "staff"
            ),
            Account(
                "gilang",
                "gilang@gmail.com",
                "gilang123",
                "staff"
            )
        )
    }
}