package com.example.ruanghukum.views.aiChat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ruanghukum.databinding.FragmentAIChatBinding

class AIChatFragment : Fragment() {

    private var _binding: FragmentAIChatBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAIChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        // TODO: Use the ViewModel
    }

    private fun setupView() {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}