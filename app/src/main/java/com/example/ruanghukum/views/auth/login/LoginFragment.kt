package com.example.ruanghukum.views.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.databinding.FragmentLoginBinding
import com.example.ruanghukum.factory.ViewModelFactory
import com.example.ruanghukum.utils.NetworkResultState

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAction()
    }

    private fun setupAction() {

        with(binding) {
            btnRegister.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment)
            )

            btnLogin.setOnClickListener {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                if (email != "" && password != "") {
                    viewModel.login(email, password).observe(requireActivity()) { resultState ->
                        when (resultState) {
                            is NetworkResultState.Success -> {
                                viewModel.saveSession(
                                    UserModel(
                                        email = resultState.data.data!!.payload!!.email!!,
                                        name = "",
                                        picture = "",
                                    )
                                )
                                showLoading(false)
                                activity?.supportFragmentManager?.popBackStack()
                            }
                            is NetworkResultState.Loading -> {
                                showLoading(true)
                            }
                            is NetworkResultState.Error -> {
                                showLoading(false)
                                Toast.makeText(requireContext(), resultState.error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Mohon isi semua data dulu ya.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}