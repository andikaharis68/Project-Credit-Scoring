package com.andika.project_credit_scoring.model.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseTotal(

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

	@field:SerializedName("totalApproved")
	val totalApproved: Int? = null,

	@field:SerializedName("totalTransaction")
	val totalTransaction: Int? = null,

	@field:SerializedName("totalCustomer")
	val totalCustomer: Int? = null,

	@field:SerializedName("totalUser")
	val totalUser: Int? = null,

	@field:SerializedName("totalRejected")
	val totalRejected: Int? = null
) : Parcelable
