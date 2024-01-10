package com.example.ruanghukum.views.documentPrep.documentPrepData

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.ruanghukum.R
import com.example.ruanghukum.databinding.FragmentDocumentPrepDataBinding

class DocumentPrepData : Fragment() {


    private var _binding: FragmentDocumentPrepDataBinding? = null
    private val binding get() = _binding!!

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

        // TODO: Use the ViewModel
    }

    private fun setupView() {
        with(binding) {
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            btnCreateDocument.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_documentPrepData_to_documentPrepPreview)
            }
        }
    }
}