package com.example.ruanghukum.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpdateProfileResponse(

	@field:SerializedName("data")
	val data: DataUpdateProfile? = null,

	@field:SerializedName("meta")
	val meta: MetaUpdateProfile? = null
) : Parcelable

@Parcelize
data class DataUpdateProfile(

	@field:SerializedName("data")
	val data: DataUser? = null,

) : Parcelable

@Parcelize
data class PayloadUpdateProfile(

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable

@Parcelize
data class DataUser(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("payload")
	val payload: PayloadUpdateProfile? = null
) : Parcelable

@Parcelize
data class MetaUpdateProfile(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable
