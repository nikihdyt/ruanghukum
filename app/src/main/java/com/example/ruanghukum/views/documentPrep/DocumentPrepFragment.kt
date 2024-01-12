package com.example.ruanghukum.views.documentPrep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        DocumentCategory(1, "Crime Review"),
        DocumentCategory(2, "Commercial Offences"),
        DocumentCategory(3, "Corruption Cases"),
        DocumentCategory(4, "Edical Negligence\nCases")
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
            documentPrepBanner.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_documentPrepFragment_to_documentPrepData)
            }
//            documentPrepBanner.setOnClickListener {
//                Navigation.findNavController(it)
//                    .navigate(R.id.action_documentPrepFragment_to_documentPrepPreview)
//            }
        }

        binding.rvCategories1.adapter = DocumentCategoryAdapter(categories1)
        binding.rvCategories2.adapter = DocumentCategoryAdapter(categories2)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}