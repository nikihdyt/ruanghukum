package com.example.ruanghukum.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ruanghukum.R
import com.example.ruanghukum.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        // TODO: Use the ViewModel
    }

    private fun setupView() {

        with(binding) {
            btnAiChat.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_aIChatFragment)
            )

            btnConsultationWithExpert.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_consultationFragment)
            )

            btnDocumentPrep.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_documentPrepFragment)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}