package com.andika.project_credit_scoring.model.roles

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestRole(

	@field:SerializedName("readAllReportByTransaction")
	val readAllReportByTransaction: Boolean? = null,

	@field:SerializedName("inputCustomer")
	val inputCustomer: Boolean? = null,

	@field:SerializedName("readAllReport")
	val readAllReport: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("readAllTransaction")
	val readAllTransaction: Boolean? = null,

	@field:SerializedName("inputTransaction")
	val inputTransaction: Boolean? = null,

	@field:SerializedName("readAllCustomer")
	val readAllCustomer: Boolean? = null,

	@field:SerializedName("approveTransaction")
	val approveTransaction: Boolean? = null
) : Parcelable
