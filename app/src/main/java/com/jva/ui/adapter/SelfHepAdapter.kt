package com.jva.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.jva.data.model.SelfHelpCategory

import com.jva.databinding.ItemSelfHelpBinding


class SelfHepAdapter(
    private val list: List<SelfHelpCategory>,
    private val onItemClick: (selfHelpCategory: SelfHelpCategory) -> Unit
) : RecyclerView.Adapter<SelfHepAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelfHepAdapter.ViewHolder {
        val binding: ItemSelfHelpBinding = ItemSelfHelpBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SelfHepAdapter.ViewHolder, position: Int) {
        holder.bindData(onItemClick, list?.get(position))
    }

    inner class ViewHolder(val binding: ItemSelfHelpBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            onItemClick: (serviceData: SelfHelpCategory) -> Unit,
            serviceData: SelfHelpCategory,
        ) {
            binding.title.text = serviceData.category_name


            binding.root.setOnClickListener{
                onItemClick(serviceData)
            }
        }
    }
}