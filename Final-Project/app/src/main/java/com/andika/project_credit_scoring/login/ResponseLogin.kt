package com.andika.project_credit_scoring.login

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ResponseLogin(

    @SerializedName("code")
    val code: Int,

    @SerializedName("data")
    val data: Data? = null,

    @SerializedName("message")
    val message: String,

    @SerializedName("timestamp")
    val timestamp: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(Data::class.java.classLoader),
        parcel.readString()!!,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(code)
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

    @SerializedName("token")
    val token: String
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
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