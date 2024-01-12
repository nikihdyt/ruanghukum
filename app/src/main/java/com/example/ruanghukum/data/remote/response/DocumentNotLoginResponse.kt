package com.example.ruanghukum.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DocumentNotLoginResponse(

	@field:SerializedName("data")
	val data: DataDocument? = null,

	@field:SerializedName("meta")
	val meta: MetaDocument? = null
) : Parcelable

@Parcelize
data class MetaDocument(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class PayloadDocument(

	@field:SerializedName("path")
	val path: String? = null
) : Parcelable

@Parcelize
data class DataDocument(

	@field:SerializedName("payload")
	val payload: PayloadDocument? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable
