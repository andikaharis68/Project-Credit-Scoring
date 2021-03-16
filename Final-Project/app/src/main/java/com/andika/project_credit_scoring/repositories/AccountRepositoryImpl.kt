package com.andika.project_credit_scoring.repositories

import androidx.lifecycle.MutableLiveData
import com.andika.project_credit_scoring.account.Account
import com.andika.project_credit_scoring.account.AccountCoba
import com.andika.project_credit_scoring.api.AccountApi
import javax.inject.Inject

class AccountRepositoryImpl: AccountRepository {

    companion object {
        var accountList = arrayListOf(
                AccountCoba(
                        "andika",
                        "andika@gmail.com",
                        "andika123",
                        "supervisor"
                ),
                AccountCoba(
                        "gilang",
                        "gilang@gmail.com",
                        "gilang123",
                        "staff"
                ),
                AccountCoba(
                        "gilang",
                        "gilang@gmail.com",
                        "gilang123",
                        "staff"
                ),
                AccountCoba(
                        "gilang",
                        "gilang@gmail.com",
                        "gilang123",
                        "staff"
                )
        )
    }

    override fun getAccount(): List<AccountCoba> {
        return accountList
    }
}