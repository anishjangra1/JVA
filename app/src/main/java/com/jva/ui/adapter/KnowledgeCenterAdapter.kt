package com.jva.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jva.data.model.Dataa
import com.jva.databinding.ItemKnowledgebankBinding


class KnowledgeCenterAdapter(
    private val list: List<Dataa>,
    private val onItemClick: (knowledgeCenter: Dataa) -> Unit
) : RecyclerView.Adapter<KnowledgeCenterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowledgeCenterAdapter.ViewHolder {
        val binding: ItemKnowledgebankBinding = ItemKnowledgebankBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: KnowledgeCenterAdapter.ViewHolder, position: Int) {
        holder.bindData(onItemClick, list?.get(position))
    }

    inner class ViewHolder(val binding: ItemKnowledgebankBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            onItemClick: (jobCategory: Dataa) -> Unit,
            jobCategory: Dataa,
        ) {
            binding.rent.text = jobCategory.category_name

            binding.root.setOnClickListener{
                onItemClick(jobCategory)
            }
        }
    }
}