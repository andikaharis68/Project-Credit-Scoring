package com.andika.project_credit_scoring.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaction(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataTransaction? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
) : Parcelable

@Parcelize
data class ListTransaction(

	@field:SerializedName("income")
	val income: Int? = null,

	@field:SerializedName("interestRate")
	val interestRate: Int? = null,

	@field:SerializedName("submitter")
	val submitter: String? = null,

	@field:SerializedName("loan")
	val loan: Int? = null,

	@field:SerializedName("notes")
	val notes: String? = null,

	@field:SerializedName("needType")
	val needType: String? = null,

	@field:SerializedName("tenor")
	val tenor: Int? = null,

	@field:SerializedName("mainLoan")
	val mainLoan: Double? = null,

	@field:SerializedName("interest")
	val interest: Double? = null,

	@field:SerializedName("installment")
	val installment: Double? = null,

	@field:SerializedName("financeCriteria")
	val financeCriteria: Boolean? = null,

	@field:SerializedName("employeeCriteria")
	val employeeCriteria: Boolean? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("installmentTotal")
	val installmentTotal: Double? = null,

	@field:SerializedName("outcome")
	val outcome: Int? = null,

	@field:SerializedName("creditRatio")
	val creditRatio: Double? = null,

	@field:SerializedName("customer")
	val customer: CustomerTransaction? = null
) : Parcelable

@Parcelize
data class DataTransaction(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("list")
	val list: List<ListTransaction?>? = null
) : Parcelable

@Parcelize
data class CustomerTransaction(

	@field:SerializedName("submitter")
	val submitter: String? = null,

	@field:SerializedName("profilePhoto")
	val profilePhoto: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("employeeType")
	val employeeType: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("idNumber")
	val idNumber: Long? = null,

	@field:SerializedName("idPhoto")
	val idPhoto: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable
