package com.andika.project_credit_scoring.presentation.account

import com.andika.project_credit_scoring.account.Account


interface AccountClickListener {
    fun onUpdate(account : Account)
}