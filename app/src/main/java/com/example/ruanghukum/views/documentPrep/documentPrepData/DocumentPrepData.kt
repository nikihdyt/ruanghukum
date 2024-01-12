package com.example.ruanghukum.views.documentPrep.documentPrepData

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.data.remote.request.Biaya
import com.example.ruanghukum.data.remote.request.DocumentNotLoginRequest
import com.example.ruanghukum.data.remote.request.Pemilik
import com.example.ruanghukum.data.remote.request.Penyewa
import com.example.ruanghukum.data.remote.request.SewaRuko
import com.example.ruanghukum.databinding.FragmentDocumentPrepDataBinding
import com.example.ruanghukum.factory.ViewModelFactory
import com.example.ruanghukum.utils.NetworkResultState
import com.example.ruanghukum.views.auth.register.RegisterViewModel

class DocumentPrepData : Fragment() {


    private var _binding: FragmentDocumentPrepDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DocumentPrepDataViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentPrepDataBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeLoading()

        viewModel.documentStatus.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val documentPath = result.getOrNull()?.data?.payload?.path
                documentPath?.let {
                    viewModel.navigateToDocumentPrepPreview(Navigation.findNavController(requireView()), it)
                }
            }
        }
    }

    private fun setupView() {
        with(binding) {
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }

            btnCreateDocument.setOnClickListener {
                // Data Ruko
                val alamatRuko = edtAlamatRuko.text.toString()
                val nomorHakMilik = edtNomorHakMilik.text.toString()
                val luasRuko = edtLuas.text.toString()
                val dayaListrik = editDayaListrik.text.toString()
                val sumberAir = edtSumberAir.text.toString()
                val hargaSewa = edtHargaSewa.text.toString()
                val jangkaWaktu = edtJangkaWaktu.text.toString()
                val tanggalMulai = edtTanggalMulai.text.toString()
                val tanggalBerakhir = edtTanggalBerakhir.text.toString()

                // Data Pemilik
                val namaPemilik = edtNamaPemilik.text.toString()
                val tempatTtlPemilik = edtTempatTtl.text.toString()
                val nomorKtpPemilik = edtNoKtp.text.toString()
                val pekerjaanPemilik = edtPekerjaan.text.toString()
                val alamatPemilik = edtAlamatPemilik.text.toString()

                // Data Penyewa
                val namaPenyewa = edtNamaPenyewa.text.toString()
                val tempatTtlPenyewa = edtTempatTtlPenyewa.text.toString()
                val nomorKtpPenyewa = edtNoKtpPenyewa.text.toString()
                val pekerjaanPenyewa = edtPekerjaanPenyewa.text.toString()
                val alamatPenyewa = edtAlamatPenyewa.text.toString()

                if (alamatRuko.isNotBlank() && nomorHakMilik.isNotBlank() && luasRuko.isNotBlank()
                    && dayaListrik.isNotBlank() && sumberAir.isNotBlank() && hargaSewa.isNotBlank()
                    && jangkaWaktu.isNotBlank() && tanggalMulai.isNotBlank() && tanggalBerakhir.isNotBlank()
                    && namaPemilik.isNotBlank() && tempatTtlPemilik.isNotBlank() && nomorKtpPemilik.isNotBlank()
                    && pekerjaanPemilik.isNotBlank() && alamatPemilik.isNotBlank() && namaPenyewa.isNotBlank()
                    && tempatTtlPenyewa.isNotBlank() && nomorKtpPenyewa.isNotBlank() && pekerjaanPenyewa.isNotBlank()
                    && alamatPenyewa.isNotBlank()
                ) {
                    progressBar.visibility = View.VISIBLE
                    // Inisialisasi objek DocumentNotLoginRequest dengan data dari pengguna
                    val documentReq = DocumentNotLoginRequest(
                        penyewa = Penyewa(
                            tempatTtl = tempatTtlPenyewa,
                            nama = namaPenyewa,
                            pekerjaan = pekerjaanPenyewa,
                            noKtp = nomorKtpPenyewa,
                            alamat = alamatPenyewa
                        ),
                        sewaRuko = SewaRuko(
                            biaya = Biaya(
                                jangkaWaktu = jangkaWaktu,
                                tanggalMulai = tanggalMulai,
                                tanggalBerakhir = tanggalBerakhir,
                                hargaSewa = hargaSewa
                            ),
                            dayaListrik = dayaListrik,
                            nomorHakMilik = nomorHakMilik,
                            luas = luasRuko,
                            sumberAir = sumberAir,
                            alamat = alamatRuko
                        ),
                        pemilik = Pemilik(
                            tempatTtl = tempatTtlPemilik,
                            nama = namaPemilik,
                            pekerjaan = pekerjaanPemilik,
                            noKtp = nomorKtpPemilik,
                            alamat = alamatPemilik
                        )
                    )

                    // Panggil fungsi untuk membuat dokumen
                    viewModel.createDocumentPrep(category = "sewa-ruko", documentReq)
                    progressBar.visibility = View.GONE
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Mohon isi semua data terlebih dahulu.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

}