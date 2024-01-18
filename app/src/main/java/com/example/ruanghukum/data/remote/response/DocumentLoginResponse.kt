package com.example.ruanghukum.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DocumentLoginResponse(

	@field:SerializedName("data")
	val data: DataDocumentLogin? = null,

	@field:SerializedName("meta")
	val meta: MetaDocumentLogin? = null
) : Parcelable

@Parcelize
data class MetaDocumentLogin(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class PayloadDocumentLogin(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable

@Parcelize
data class DataDocumentLogin(

	@field:SerializedName("payload")
	val payload: PayloadDocumentLogin? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable
