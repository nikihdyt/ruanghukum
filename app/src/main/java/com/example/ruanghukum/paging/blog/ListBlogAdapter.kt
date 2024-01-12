package com.example.ruanghukum.paging.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ruanghukum.R
import com.example.ruanghukum.data.remote.response.PayloadItemBlog
import com.example.ruanghukum.databinding.ItemArticleBinding

class ListBlogAdapter: ListAdapter<PayloadItemBlog, ListBlogAdapter.MyViewHolder>(ListBlogAdapter.DIFF_CALLBACK) {
    private lateinit var onItemClickCallback : OnItemClickCallback

    class MyViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: PayloadItemBlog){
            Glide.with(itemView.context)
                .load(article.image)
                .placeholder(R.drawable.baseline_broken_image_24)
                .error(R.drawable.baseline_broken_image_24)
                .into(binding.imgArticle)
            binding.tvTitle.text = article.title
            binding.tvPreview.text = article.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(article) }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(article: PayloadItemBlog)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PayloadItemBlog>() {
            override fun areItemsTheSame(oldItem: PayloadItemBlog, newItem: PayloadItemBlog): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: PayloadItemBlog, newItem: PayloadItemBlog): Boolean {
                return oldItem == newItem
            }
        }
    }
}