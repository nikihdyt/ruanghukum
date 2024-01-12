package com.example.ruanghukum.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetAllBlogResponse(

	@field:SerializedName("data")
	val data: DataGetAllBlog? = null,

	@field:SerializedName("meta")
	val meta: MetaGetAllBlog? = null
) : Parcelable

@Parcelize
data class MetaGetAllBlog(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class PayloadItemBlog(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("flag")
	val flag: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
) : Parcelable

@Parcelize
data class DataGetAllBlog(

	@field:SerializedName("payload")
	val payload: List<PayloadItemBlog>? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable
