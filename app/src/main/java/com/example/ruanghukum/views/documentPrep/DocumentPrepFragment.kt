package com.example.ruanghukum.views.documentPrep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.dummy.DocumentCategory
import com.example.ruanghukum.databinding.FragmentDocumentPrepBinding

class DocumentPrepFragment : Fragment() {

    private var _binding: FragmentDocumentPrepBinding? = null
    private val binding get() = _binding!!

    private val categories1 = listOf(
        DocumentCategory(1, "Surat Sewa Rumah"),
        DocumentCategory(2, "Surat Sewa Ruko")
    )

    private val categories2 = listOf(
        DocumentCategory(1, "Formulir Pencatatan Lisensi"),
        DocumentCategory(2, "Surat Pengalihan Hak Cipta"),
        DocumentCategory(3, "Perubahan Nama Hak Cipta"),
        DocumentCategory(4, "Surat Ket UMK")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentPrepBinding.inflate(inflater, container, false)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        // TODO: Use the ViewModel
    }

    private fun setupView() {
        with(binding) {
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            sewaRukoCard.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_documentPrepFragment_to_documentPrepData)
            }
            sewaRumahCard.setOnClickListener {
                Toast.makeText(requireContext(), "Dokumen Belum Tersedia", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvCategories2.adapter = DocumentCategoryAdapter(categories2)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}