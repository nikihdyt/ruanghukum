package com.example.ruanghukum.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruanghukum.R
import com.example.ruanghukum.data.remote.response.PayloadItemBlog
import com.example.ruanghukum.databinding.FragmentHomeBinding
import com.example.ruanghukum.factory.ViewModelFactory
import com.example.ruanghukum.paging.blog.ListBlogAdapter
import com.example.ruanghukum.utils.NetworkResultState

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

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
        updateArticles()
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

            btnSeeAll.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_navigation_updates)
            )
        }

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvArticles.layoutManager = layoutManager
    }

    private fun updateArticles() {
        viewModel.getBlog().observe(requireActivity()) { resultState ->
            when (resultState) {
                is NetworkResultState.Success -> {
                    setArticles(resultState.data.data!!.payload!!)
                }
                is NetworkResultState.Loading -> {
                }
                is NetworkResultState.Error -> {
                }
            }
        }
    }

    private fun setArticles(article: List<PayloadItemBlog>) {

        val adapter = ListBlogAdapter()
        adapter.submitList(article)
        binding.rvArticles.adapter = adapter

        adapter.setOnItemClickCallback(object : ListBlogAdapter.OnItemClickCallback {
            override fun onItemClicked(article: PayloadItemBlog) {

                viewModel.navigateToArticleDetail(
                    Navigation.findNavController(requireView()),
                    article.title!!,
                    article.content!!,
                    article.image!!
                )
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}