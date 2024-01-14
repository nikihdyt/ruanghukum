package com.example.ruanghukum.views.documentPrep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.dummy.DocumentCategory
import com.example.ruanghukum.databinding.ItemDocumentCategoryBinding

class DocumentCategoryAdapter(private val categories: List<DocumentCategory> ): RecyclerView.Adapter<DocumentCategoryAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemDocumentCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val categoryName: TextView = binding.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDocumentCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryName.text = category.name
        holder.itemView.setOnClickListener{
            Toast.makeText(it.context, "Dokumen Belum Tersedia", Toast.LENGTH_SHORT).show()

        }
    }
}