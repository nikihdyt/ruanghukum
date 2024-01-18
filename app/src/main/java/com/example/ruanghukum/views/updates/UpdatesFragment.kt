package com.example.ruanghukum.views.updates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruanghukum.data.remote.response.PayloadItemBlog
import com.example.ruanghukum.databinding.FragmentUpdatesBinding
import com.example.ruanghukum.factory.ViewModelFactory
import com.example.ruanghukum.paging.blog.ListBlogAdapter
import com.example.ruanghukum.utils.NetworkResultState

class UpdatesFragment : Fragment() {

    private var _binding: FragmentUpdatesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UpdatesViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdatesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        updateArticles()
    }

    private fun setupView() {
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