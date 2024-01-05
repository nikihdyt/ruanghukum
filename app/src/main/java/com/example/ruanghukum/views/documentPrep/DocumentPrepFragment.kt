package com.example.ruanghukum.views.documentPrep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ruanghukum.R

class DocumentPrepFragment : Fragment() {

    companion object {
        fun newInstance() = DocumentPrepFragment()
    }

    private lateinit var viewModel: DocumentPrepViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_document_prep, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DocumentPrepViewModel::class.java)
        // TODO: Use the ViewModel
    }

}