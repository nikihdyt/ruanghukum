package com.example.ruanghukum.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(

	@field:SerializedName("data")
	val data: DataLogin? = null,

	@field:SerializedName("meta")
	val meta: MetaLogin? = null
) : Parcelable

@Parcelize
data class MetaLogin(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class PayloadLogin(

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable

@Parcelize
data class DataLogin(

	@field:SerializedName("payload")
	val payload: PayloadLogin? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable
