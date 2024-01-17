package com.example.ruanghukum.views.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.databinding.FragmentProfileBinding
import com.example.ruanghukum.factory.ViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSession().observe(requireActivity()) { user ->
            val session = UserModel(
                user.email,
                user.name,
                user.picture,
                user.token
            )

            setupView(session)
        }
    }

    private fun setupView(session: UserModel) {
        if (session.email != "") {
            with(binding) {
                btnLogin.visibility = View.GONE
                layoutProfile.visibility = View.VISIBLE
                btnEditProfile.visibility = View.VISIBLE
                btnLogout.visibility = View.VISIBLE

                tvProfileName.text = session.name
                tvEmail.text = session.email
                Glide.with(root)
                    .load(session.picture)
                    .placeholder(R.drawable.baseline_account_circle_24)
                    .error(R.drawable.baseline_account_circle_24)
                    .into(ivProfilePic)
            }
        } else {
            with(binding) {
                btnLogin.visibility = View.VISIBLE
                layoutProfile.visibility = View.GONE
                btnEditProfile.visibility = View.GONE
                btnLogout.visibility = View.GONE
            }
        }

        binding.btnLogin.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_profile_to_loginFragment)
        )

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

}