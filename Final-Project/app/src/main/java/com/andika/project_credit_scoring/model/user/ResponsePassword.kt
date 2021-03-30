package com.andika.project_credit_scoring.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponsePassword(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
) : Parcelable
