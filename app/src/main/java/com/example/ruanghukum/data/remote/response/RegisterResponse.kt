package com.example.ruanghukum.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterResponse(

	@field:SerializedName("data")
	val data: DataRegister? = null,
) : Parcelable

@Parcelize
data class PayloadRegister(

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable

@Parcelize
data class DataRegister(

	@field:SerializedName("payload")
	val payload: PayloadRegister? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable
