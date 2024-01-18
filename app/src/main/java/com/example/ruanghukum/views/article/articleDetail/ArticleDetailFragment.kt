package com.example.ruanghukum.views.article.articleDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ruanghukum.R
import com.example.ruanghukum.databinding.FragmentArticleDetailBinding

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

        val title = arguments?.getString("extra_title")
        val content = arguments?.getString("extra_content")
        val image = arguments?.getString("extra_image")
        setupView(title!!, content!!, image!!)
    }

    private fun setupView(
        title: String,
        content: String,
        image: String
    ) {
        Log.d("DEBUG", "setupView: $image")
        with(binding) {
            Glide.with(root)
                .load(image)
                .placeholder(R.drawable.baseline_account_circle_24)
                .error(R.drawable.baseline_account_circle_24)
                .into(img)
            tvTitle.text = title
            tvContent.text = content

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