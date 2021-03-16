package com.andika.project_credit_scoring.login

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readValue(Int::class.java.classLoader) as? Int,
			parcel.readParcelable(Data::class.java.classLoader),
			parcel.readString(),
			parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(code)
		parcel.writeParcelable(data, flags)
		parcel.writeString(message)
		parcel.writeString(timestamp)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ResponseLogin> {
		override fun createFromParcel(parcel: Parcel): ResponseLogin {
			return ResponseLogin(parcel)
		}

		override fun newArray(size: Int): Array<ResponseLogin?> {
			return arrayOfNulls(size)
		}
	}
}


data class Data(

	@field:SerializedName("roles")
	val roles: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(roles)
		parcel.writeString(email)
		parcel.writeString(token)
		parcel.writeString(username)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Data> {
		override fun createFromParcel(parcel: Parcel): Data {
			return Data(parcel)
		}

		override fun newArray(size: Int): Array<Data?> {
			return arrayOfNulls(size)
		}
	}
}
