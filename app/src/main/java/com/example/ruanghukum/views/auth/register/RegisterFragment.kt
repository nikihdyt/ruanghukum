package com.example.ruanghukum.views.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.databinding.FragmentRegisterBinding
import com.example.ruanghukum.factory.ViewModelFactory
import com.example.ruanghukum.utils.NetworkResultState
import com.google.android.material.datepicker.MaterialDatePicker

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupAction()
    }

    private fun setupAction() {

        with(binding) {
            btnLogin.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_registerFragment_to_loginFragment)
            )

            btnRegister.setOnClickListener {
                val fullname = etName.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val phoneNumber = etPhone.text.toString()
                val address = etAddress.text.toString()
                val gender = if (inputGender.text.toString() == "Perempuan") "female" else "male"
                val jobTitle = etJobTitle.text.toString()
                val idCardNumber = etNik.text.toString()
                val birthDate = if (birthDateInputLayout.text.toString() != "Pilih tanggal") birthDateInputLayout.text.toString() else ""

                viewModel.register(
                    fullname,
                    email,
                    password,
                    address,
                    phoneNumber,
                    gender,
                    jobTitle,
                    idCardNumber,
                    birthDate,
                    ).observe(requireActivity()) { resultState ->
                    when (resultState) {
                        is NetworkResultState.Success -> {
                            val rEmail = resultState.data.data!!.payload!!.email!!
                            val rName = resultState.data.data!!.payload!!.fullname!!

                            viewModel.login(email, password).observe(requireActivity()) { resultState ->
                                when (resultState) {
                                    is NetworkResultState.Success -> {
                                        viewModel.saveSession(
                                            UserModel(
                                                email = rEmail,
                                                name = rName,
                                                picture = "",
                                                token = ""
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
                                    else -> {}
                                }
                            }
                        }
                        is NetworkResultState.Loading -> {
                            showLoading(true)
                        }
                        is NetworkResultState.Error -> {
                            showLoading(false)
                            Toast.makeText(requireContext(), resultState.error, Toast.LENGTH_SHORT).show()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun setupView() {
        val items = resources.getStringArray(R.array.genders)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_menu, items)
        binding.inputGender.setAdapter(adapter)

        val materialDateBuilder: MaterialDatePicker.Builder<*> =
            MaterialDatePicker
                .Builder
                .datePicker()
                .setTitleText("Pilih tanggal lahir")
        val materialDatePicker = materialDateBuilder.build()
        binding.birthDateInputLayout.setOnClickListener() {
            activity?.let { it1 -> materialDatePicker.show(it1.supportFragmentManager, DATE_PICKER_TAG) }
        }
        materialDatePicker.addOnPositiveButtonClickListener {
            binding.birthDateInputLayout.text = materialDatePicker.headerText
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val DATE_PICKER_TAG = "DATE_PICKER"
    }
}