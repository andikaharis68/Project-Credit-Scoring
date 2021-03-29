package com.andika.project_credit_scoring.model.roles

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseRole(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataRole? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
) : Parcelable

@Parcelize
data class DataRole(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("list")
	val list: List<ListRole?>? = null
) : Parcelable

@Parcelize
data class ListRole(

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

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("inputTransaction")
	val inputTransaction: Boolean? = null,

	@field:SerializedName("readAllCustomer")
	val readAllCustomer: Boolean? = null,

	@field:SerializedName("approveTransaction")
	val approveTransaction: Boolean? = null,

	@field:SerializedName("master")
	val master: Boolean? = null
) : Parcelable
