package com.example.ruanghukum.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DocumentHistoryResponse(

	@field:SerializedName("data")
	val data: DataDocumentHistory? = null,

	@field:SerializedName("meta")
	val meta: MetaDocumentHistory? = null
) : Parcelable

@Parcelize
data class MetaDocumentHistory(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class DataDocumentHistory(

	@field:SerializedName("payload")
	val payload: List<PayloadItemDocumentHistory?>? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class PayloadItemDocumentHistory(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: String? = null
) : Parcelable