package com.example.ruanghukum.views.updates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruanghukum.R
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
//                val bundle = Bundle()
//                bundle.putString(EXTRA_TITLE, article.title)
//                bundle.putString(EXTRA_CONTENT, article.content)
//                bundle.putString(EXTRA_IMAGE, article.image)

                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_articleDetailFragment)
                Toast.makeText(requireContext(), article.title, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_CONTENT = "extra_content"
        const val EXTRA_IMAGE = "extra_image"
    }

}