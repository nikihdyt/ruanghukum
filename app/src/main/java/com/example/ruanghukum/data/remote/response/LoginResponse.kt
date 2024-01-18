package com.example.ruanghukum.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

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
data class DataLogin(

	@field:SerializedName("payload")
	val payload: PayloadLogin? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("token")
	val token: String? = null
) : Parcelable

@Parcelize
data class PayloadLogin(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("birth_date")
	val birthDate: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("profile_picture")
	val profilePicture: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("id_card_number")
	val idCardNumber: String? = null,

	@field:SerializedName("job_title")
	val jobTitle: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable