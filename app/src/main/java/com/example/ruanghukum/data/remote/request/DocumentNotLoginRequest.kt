package com.example.ruanghukum.data.remote.request

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DocumentNotLoginRequest(

	@field:SerializedName("penyewa")
	val penyewa: Penyewa? = null,

	@field:SerializedName("sewaRuko")
	val sewaRuko: SewaRuko? = null,

	@field:SerializedName("pemilik")
	val pemilik: Pemilik? = null
) : Parcelable

@Parcelize
data class Biaya(

	@field:SerializedName("jangka_waktu")
	val jangkaWaktu: String? = null,

	@field:SerializedName("tanggal_mulai")
	val tanggalMulai: String? = null,

	@field:SerializedName("tanggal_berakhir")
	val tanggalBerakhir: String? = null,

	@field:SerializedName("harga_sewa")
	val hargaSewa: String? = null
) : Parcelable

@Parcelize
data class Penyewa(

	@field:SerializedName("tempat_ttl")
	val tempatTtl: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("pekerjaan")
	val pekerjaan: String? = null,

	@field:SerializedName("no_ktp")
	val noKtp: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
) : Parcelable

@Parcelize
data class SewaRuko(

	@field:SerializedName("biaya")
	val biaya: Biaya? = null,

	@field:SerializedName("daya_listrik")
	val dayaListrik: String? = null,

	@field:SerializedName("nomor_hak_milik")
	val nomorHakMilik: String? = null,

	@field:SerializedName("luas")
	val luas: String? = null,

	@field:SerializedName("sumber_air")
	val sumberAir: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
) : Parcelable

@Parcelize
data class Pemilik(

	@field:SerializedName("tempat_ttl")
	val tempatTtl: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("pekerjaan")
	val pekerjaan: String? = null,

	@field:SerializedName("no_ktp")
	val noKtp: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
) : Parcelable
