package com.example.ruanghukum.views.consultation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ruanghukum.databinding.FragmentConsultationBinding

class ConsultationFragment : Fragment() {

    private var _binding: FragmentConsultationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConsultationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        // TODO: Use the ViewModel
    }

    private fun setupView() {
        with(binding) {
            btnBack.setOnClickListener{
                    activity?.onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}