package com.andika.project_credit_scoring.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUser(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("profilePicture")
	val profilePicture: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("isVerified")
	val isVerified: Boolean? = null,

	@field:SerializedName("dateRegister")
	val dateRegister: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable
