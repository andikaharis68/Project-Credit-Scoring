package com.andika.project_credit_scoring.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataAccount? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
) : Parcelable

@Parcelize
data class DataAccount(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("list")
	val list: List<ListAccount?>? = null
) : Parcelable

@Parcelize
data class ListAccount(

	@field:SerializedName("profilePicture")
	val profilePicture: String? = null,

	@field:SerializedName("isVerified")
	val isVerified: Boolean? = null,

	@field:SerializedName("roles")
	val roles: String? = null,

	@field:SerializedName("dateRegister")
	val dateRegister: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("active")
	val active: Boolean? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable
