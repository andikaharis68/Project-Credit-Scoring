package com.andika.project_credit_scoring.presentation.account

import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.ListAccount
import com.andika.project_credit_scoring.entity.RequestAccount

interface AccountClickListener {
    fun onDelete(id : String)
}