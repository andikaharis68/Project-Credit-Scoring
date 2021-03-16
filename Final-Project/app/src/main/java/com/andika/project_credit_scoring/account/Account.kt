package com.andika.project_credit_scoring.account

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Account (
        @SerializedName("name")
        val name: String,

        @SerializedName("email")
        val email: String,

        @SerializedName("password")
        val password: String,

        @SerializedName("role")
        val role: String
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(role)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Account> {
        override fun createFromParcel(parcel: Parcel): Account {
            return Account(parcel)
        }

        override fun newArray(size: Int): Array<Account?> {
            return arrayOfNulls(size)
        }
    }
}