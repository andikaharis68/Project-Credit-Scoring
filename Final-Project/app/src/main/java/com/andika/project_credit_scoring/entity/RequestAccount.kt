package com.andika.project_credit_scoring.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestAccount(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("role")
	val role: String? = null
) : Parcelable
