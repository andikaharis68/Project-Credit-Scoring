package com.andika.project_credit_scoring.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestPassword(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("oldPassword")
	val oldPassword: String? = null
) : Parcelable
