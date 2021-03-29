package com.andika.project_credit_scoring.model.transaction

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestApproval(

	@field:SerializedName("approve")
	val approve: Boolean? = null
) : Parcelable
