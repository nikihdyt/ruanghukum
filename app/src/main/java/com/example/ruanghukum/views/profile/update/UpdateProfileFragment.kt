package com.example.ruanghukum.views.profile.update

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.databinding.FragmentUpdateProfileBinding
import com.example.ruanghukum.factory.ViewModelFactory
import com.example.ruanghukum.utils.ImageFile.reduceFileImage
import com.example.ruanghukum.utils.ImageFile.uriToFile
import com.example.ruanghukum.utils.NetworkResultState
import com.google.android.material.datepicker.MaterialDatePicker

class UpdateProfileFragment : Fragment() {

    private var _binding: FragmentUpdateProfileBinding? = null
    private val binding get() = _binding!!

    private var currentImageUri: Uri? = null

    private val viewModel by viewModels<UpdateProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSession().observe(requireActivity()) { user ->
            val session = UserModel(
                user.email,
                user.name,
                user.picture,
                user.address,
                user.phoneNumber,
                user.gender,
                user.jobTitle,
                user.idCardNumber,
                user.birthDate,
                user.token,
            )

            setupView(session)
            setupAction(user.token)
        }
    }

    private fun setupView(
        session: UserModel
    ) {
        with(binding) {
            etName.setText(session.name)
            etAddress.setText(session.address)
            etPhone.setText(session.phoneNumber)
            inputGender.setText(session.gender)
            etJobTitle.setText(session.jobTitle)
            etNik.setText(session.idCardNumber)
            birthDateInputLayout.text = session.birthDate
            Glide.with(root)
                .load(session.picture)
                .placeholder(R.drawable.baseline_account_circle_24)
                .error(R.drawable.baseline_account_circle_24)
                .into(ivProfilePic)
        }
    }

    private fun setupAction(token: String) {
        // edit gender
        val items = resources.getStringArray(R.array.genders)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_menu, items)

        // edit birth date
        val materialDateBuilder: MaterialDatePicker.Builder<*> =
            MaterialDatePicker
                .Builder
                .datePicker()
                .setTitleText("Pilih tanggal lahir")
        val materialDatePicker = materialDateBuilder.build()

        with(binding) {
            inputGender.setAdapter(adapter)

            birthDateInputLayout.setOnClickListener() {
                activity?.let { it1 -> materialDatePicker.show(it1.supportFragmentManager,
                    DATE_PICKER_TAG
                ) }
            }
            materialDatePicker.addOnPositiveButtonClickListener {
                birthDateInputLayout.text = materialDatePicker.headerText
            }

            btnEditPic.setOnClickListener() {
                launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            btnSaveChanges.setOnClickListener() {
                updateProfile(token)
                Log.d("UpdateProfileFragment", "ON CLICK APDET PROFIL")
            }

            btnBack.setOnClickListener() {
                onDestroy()
            }
        }
    }

    private fun updateProfile(token: String) {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, requireContext()).reduceFileImage()

            viewModel.updateProfile(
                token,
                imageFile,
                binding.etName.text.toString(),
                binding.etAddress.text.toString(),
                binding.etPhone.text.toString(),
                binding.inputGender.text.toString(),
                binding.etJobTitle.text.toString(),
                binding.etNik.text.toString(),
                binding.birthDateInputLayout.text.toString(),
            ).observe(requireActivity()) { resultState ->
                when (resultState) {
                    is NetworkResultState.Success -> {
                        Toast.makeText(requireContext(), "Berhasil mengubah profil", Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResultState.Loading -> {
                    }
                    is NetworkResultState.Error -> {
                        Toast.makeText(requireContext(), resultState.error, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }

    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("UpdateProfileFragment", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("UpdateProfileFragment", "showImage: $it")
            Glide.with(binding.root)
                .load(it)
                .placeholder(R.drawable.baseline_account_circle_24)
                .error(R.drawable.baseline_account_circle_24)
                .into(binding.ivProfilePic)
        }
    }

    companion object {
        const val DATE_PICKER_TAG = "DATE_PICKER_UPDATE_PROFILE"
    }
}