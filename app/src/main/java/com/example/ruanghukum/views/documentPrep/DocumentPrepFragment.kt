package com.example.ruanghukum.views.documentPrep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ruanghukum.databinding.FragmentDocumentPrepBinding

class DocumentPrepFragment : Fragment() {

    private var _binding: FragmentDocumentPrepBinding? = null
    private val binding get() = _binding!!

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
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}