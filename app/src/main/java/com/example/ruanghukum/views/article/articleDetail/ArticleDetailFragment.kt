package com.example.ruanghukum.views.article.articleDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ruanghukum.R
import com.example.ruanghukum.databinding.FragmentArticleDetailBinding
import com.example.ruanghukum.views.home.HomeFragment

class ArticleDetailFragment : Fragment() {

    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupView()
    }

    private fun setupView() {
        with(binding) {
            tvTitle.text = arguments?.getString(HomeFragment.EXTRA_TITLE)
            tvContent.text = arguments?.getString(HomeFragment.EXTRA_CONTENT)
            Glide.with(root)
                .load(arguments?.getString(HomeFragment.EXTRA_IMAGE))
                .placeholder(R.drawable.baseline_account_circle_24)
                .error(R.drawable.baseline_account_circle_24)
                .into(image)

            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}