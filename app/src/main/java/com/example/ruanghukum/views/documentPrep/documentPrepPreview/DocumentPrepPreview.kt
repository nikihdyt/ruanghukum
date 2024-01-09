package com.example.ruanghukum.views.documentPrep.documentPrepPreview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ruanghukum.R
import com.example.ruanghukum.databinding.FragmentDocumentPrepPreviewBinding
import com.github.barteksc.pdfviewer.util.FitPolicy


class DocumentPrepPreview : Fragment() {

    private var _binding: FragmentDocumentPrepPreviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentPrepPreviewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
        }

        val pdfView = binding.pdfView

        pdfView.fromAsset("doc_test.pdf")
            .pageFitPolicy(FitPolicy.WIDTH)
            .fitEachPage(true)
            .load()
    }


}