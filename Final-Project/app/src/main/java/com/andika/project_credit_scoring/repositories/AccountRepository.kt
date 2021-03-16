package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.account.Account
import com.andika.project_credit_scoring.account.AccountCoba


interface AccountRepository {
    fun getAccount() : List<AccountCoba>
}