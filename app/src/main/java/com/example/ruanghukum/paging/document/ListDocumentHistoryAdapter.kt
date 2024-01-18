package com.example.ruanghukum.paging.document

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ruanghukum.data.remote.response.PayloadItemDocumentHistory
import com.example.ruanghukum.databinding.ItemDocumentBinding

class ListDocumentHistoryAdapter: ListAdapter<PayloadItemDocumentHistory, ListDocumentHistoryAdapter.MyViewHolder>(
    ListDocumentHistoryAdapter.DIFF_CALLBACK) {

    private lateinit var onItemClickCallback : OnItemClickCallback

    class MyViewHolder(val binding: ItemDocumentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(doc: PayloadItemDocumentHistory){
            binding.tvTitle.text = doc.name ?: doc.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDocumentHistoryAdapter.MyViewHolder {
        val binding = ItemDocumentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListDocumentHistoryAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListDocumentHistoryAdapter.MyViewHolder, position: Int) {
        val doc = getItem(position)
        holder.bind(doc)
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(doc) }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(doc: PayloadItemDocumentHistory)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PayloadItemDocumentHistory>() {
            override fun areItemsTheSame(oldItem: PayloadItemDocumentHistory, newItem: PayloadItemDocumentHistory): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: PayloadItemDocumentHistory, newItem: PayloadItemDocumentHistory): Boolean {
                return oldItem == newItem
            }
        }
    }
}