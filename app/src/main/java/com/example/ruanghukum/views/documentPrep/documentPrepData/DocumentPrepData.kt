package com.example.ruanghukum.views.documentPrep.documentPrepData

import android.os.Bundle
import android.util.Log
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
import com.example.ruanghukum.data.remote.request.BiayaLogin
import com.example.ruanghukum.data.remote.request.DocumentLoginRequest
import com.example.ruanghukum.data.remote.request.DocumentNotLoginRequest
import com.example.ruanghukum.data.remote.request.Pemilik
import com.example.ruanghukum.data.remote.request.Penyewa
import com.example.ruanghukum.data.remote.request.PenyewaLogin
import com.example.ruanghukum.data.remote.request.SewaRuko
import com.example.ruanghukum.data.remote.request.SewaRukoLogin
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

    var myToken = ""

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
                    viewModel.navigateToDocumentPrepPreview(
                        Navigation.findNavController(requireView()),
                        it
                    )
                    resetForm()

                }
            }
        }

        viewModel.documentStatusLogin.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val documentPath = result.getOrNull()?.data?.payload?.path
                documentPath?.let {
                    viewModel.navigateToDocumentPrepPreview(
                        Navigation.findNavController(requireView()),
                        it
                    )
                    resetForm()

                }
            }
        }

        viewModel.documentStatus.observe(viewLifecycleOwner) { result ->
            handleDocumentResponse(result)
        }

        viewModel.documentStatusLogin.observe(viewLifecycleOwner) { result ->
            handleDocumentResponse(result)
        }

        viewModel.getSession().observe(requireActivity()) { user ->
            val session = UserModel(
                user.email,
                user.name,
                user.picture,
                user.token
            )
            myToken = "Bearer " + user.token
            Log.d("User Session", "$session")
            Log.d("Bearer Token", "$myToken")
            if (user.email.isNullOrEmpty()) {
                binding.layoutDataPemilik.visibility = View.VISIBLE
            } else {
                binding.layoutDataPemilik.visibility = View.GONE
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

                if (binding.layoutDataPemilik.visibility == View.VISIBLE) {
                    // Validate "Data Pemilik" fields only if they are visible
                    if (namaPemilik.isBlank() || tempatTtlPemilik.isBlank() || nomorKtpPemilik.isBlank()
                        || pekerjaanPemilik.isBlank() || alamatPemilik.isBlank()
                    ) {
                        Toast.makeText(
                            requireContext(),
                            "Mohon isi semua data pemilik terlebih dahulu.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }
                }

                // Validate other fields (Data Ruko and Data Penyewa)
                if (alamatRuko.isNotBlank() && nomorHakMilik.isNotBlank() && luasRuko.isNotBlank()
                    && dayaListrik.isNotBlank() && sumberAir.isNotBlank() && hargaSewa.isNotBlank()
                    && jangkaWaktu.isNotBlank() && tanggalMulai.isNotBlank() && tanggalBerakhir.isNotBlank()
                    && namaPenyewa.isNotBlank() && tempatTtlPenyewa.isNotBlank() && nomorKtpPenyewa.isNotBlank()
                    && pekerjaanPenyewa.isNotBlank() && alamatPenyewa.isNotBlank()
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

                    val documentReqLogin = DocumentLoginRequest(
                        penyewa = PenyewaLogin(
                            tempatTtl = tempatTtlPenyewa,
                            nama = namaPenyewa,
                            pekerjaan = pekerjaanPenyewa,
                            noKtp = nomorKtpPenyewa,
                            alamat = alamatPenyewa
                        ),
                        sewaRuko = SewaRukoLogin(
                            biaya = BiayaLogin(
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
                    )

                    viewModel.getSession().observe(requireActivity()) { user ->
                        val session = UserModel(
                            user.email,
                            user.name,
                            user.picture,
                            user.token
                        )
                        myToken = "Bearer " + user.token
                        Log.d("User Session", "$session")
                        Log.d("Bearer Token", "$myToken")
                        if (user.email.isNullOrEmpty()) {
                            viewModel.createDocumentPrep(myToken, category = "sewa-ruko", documentReq)

                        } else {
                            viewModel.createDocumentPrepLogin(myToken, category = "sewa-ruko", documentReqLogin)
                        }
                    }

                    // Panggil fungsi untuk membuat dokumen
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

    private fun handleDocumentResponse(result: Result<*>) {
        if (result.isFailure) {
            val errorMessage = (result.exceptionOrNull()?.message ?: "Unknown error").toString()
            showToast(errorMessage)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), "Document Response Error: $message", Toast.LENGTH_SHORT).show()
    }

    private fun resetForm() {
        with(binding) {
            edtAlamatRuko.text?.clear()
            edtNomorHakMilik.text?.clear()
            edtLuas.text?.clear()
            editDayaListrik.text?.clear()
            edtSumberAir.text?.clear()
            edtHargaSewa.text?.clear()
            edtJangkaWaktu.text?.clear()
            edtTanggalMulai.text?.clear()
            edtTanggalBerakhir.text?.clear()

            edtNamaPemilik.text?.clear()
            edtTempatTtl.text?.clear()
            edtNoKtp.text?.clear()
            edtPekerjaan.text?.clear()
            edtAlamatPemilik.text?.clear()

            edtNamaPenyewa.text?.clear()
            edtTempatTtlPenyewa.text?.clear()
            edtNoKtpPenyewa.text?.clear()
            edtPekerjaanPenyewa.text?.clear()
            edtAlamatPenyewa.text?.clear()

            // Atur ulang visibility form pemilik (jika diperlukan)
            layoutDataPemilik?.visibility = View.GONE
        }
    }

}