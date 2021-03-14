package com.andika.project_credit_scoring.login

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class RequestLogin (
        @SerializedName("password")
        val password: String,

        @SerializedName("username")
        val username: String
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(password)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RequestLogin> {
        override fun createFromParcel(parcel: Parcel): RequestLogin {
            return RequestLogin(parcel)
        }

        override fun newArray(size: Int): Array<RequestLogin?> {
            return arrayOfNulls(size)
        }
    }
}
