package com.example.ruanghukum.views.consultation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ruanghukum.R

class ConsultationFragment : Fragment() {

    companion object {
        fun newInstance() = ConsultationFragment()
    }

    private lateinit var viewModel: ConsultationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_consultation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConsultationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}