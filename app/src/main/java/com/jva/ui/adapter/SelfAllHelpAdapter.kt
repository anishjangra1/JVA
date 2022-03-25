package com.jva.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jva.data.model.AllCategory

import com.jva.databinding.ItemSelfSubHelpBinding


class SelfAllHelpAdapter(
    private val list: List<AllCategory>,
    private val onItemClick: (selfHelpCategory: AllCategory) -> Unit
) : RecyclerView.Adapter<SelfAllHelpAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelfAllHelpAdapter.ViewHolder {
        val binding: ItemSelfSubHelpBinding = ItemSelfSubHelpBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SelfAllHelpAdapter.ViewHolder, position: Int) {
        holder.bindData(onItemClick, list?.get(position))
    }

    inner class ViewHolder(val binding: ItemSelfSubHelpBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            onItemClick: (serviceData: AllCategory) -> Unit,
            serviceData: AllCategory,
        ) {
            binding.title.text = serviceData.other_sub_category


            binding.root.setOnClickListener{
                onItemClick(serviceData)
            }
        }
    }
}