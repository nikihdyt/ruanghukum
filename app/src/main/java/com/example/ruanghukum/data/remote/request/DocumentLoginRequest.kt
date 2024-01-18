package com.example.ruanghukum.data.remote.request

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DocumentLoginRequest(

	@field:SerializedName("penyewa")
	val penyewa: PenyewaLogin? = null,

	@field:SerializedName("sewaRuko")
	val sewaRuko: SewaRukoLogin? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable

@Parcelize
data class SewaRukoLogin(

	@field:SerializedName("biaya")
	val biaya: BiayaLogin? = null,

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
data class BiayaLogin(

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
data class PenyewaLogin(

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
