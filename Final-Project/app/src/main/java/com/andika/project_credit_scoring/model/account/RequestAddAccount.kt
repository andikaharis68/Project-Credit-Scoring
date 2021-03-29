package com.andika.project_credit_scoring.model.account

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestAddAccount(

	@field:SerializedName("profilePicture")
	val profilePicture: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("role")
	val role: String? = null

) : Parcelable
