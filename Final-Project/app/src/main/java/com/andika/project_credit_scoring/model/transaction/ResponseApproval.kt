package com.andika.project_credit_scoring.model.transaction

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseApproval(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataApproval? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
) : Parcelable

@Parcelize
data class DataApproval(

	@field:SerializedName("approve")
	val approve: Boolean? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("transaction")
	val transaction: TransactionApproval? = null
) : Parcelable

@Parcelize
data class NeedTypeApproval(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class TransactionApproval(

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
	val needType: NeedTypeApproval? = null,

	@field:SerializedName("tenor")
	val tenor: Int? = null,

	@field:SerializedName("mainLoan")
	val mainLoan: Double? = null,

	@field:SerializedName("interest")
	val interest: Double? = null,

	@field:SerializedName("installment")
	val installment: Double? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("installmentTotal")
	val installmentTotal: Double? = null,

	@field:SerializedName("outcome")
	val outcome: Int? = null,

	@field:SerializedName("customer")
	val customer: Customer? = null
) : Parcelable

@Parcelize
data class CustomerApproval(

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
	val idNumber: Int? = null,

	@field:SerializedName("idPhoto")
	val idPhoto: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable
