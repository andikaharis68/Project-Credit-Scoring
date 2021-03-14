package com.andika.project_credit_scoring.login

import com.google.gson.annotations.SerializedName

data class ValidationLogin (
    @field:SerializedName("email")
    val email: Boolean,

    @field:SerializedName("password")
    val username: Boolean
    )