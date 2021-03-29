package com.andika.project_credit_scoring.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseLogin(

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

	@field:SerializedName("roles")
	val roles: String? = null,

	@field:SerializedName("inputCustomer")
	val inputCustomer: Boolean? = null,

	@field:SerializedName("readAllReport")
	val readAllReport: Boolean? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("readAllCustomer")
	val readAllCustomer: Boolean? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("master")
	val master: Boolean? = null,

	@field:SerializedName("readAllReportByTransaction")
	val readAllReportByTransaction: Boolean? = null,

	@field:SerializedName("readAllTransaction")
	val readAllTransaction: Boolean? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("inputTransaction")
	val inputTransaction: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("approveTransaction")
	val approveTransaction: Boolean? = null
) : Parcelable
