package com.jva.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.jva.data.model.SelfHelpSubCategory

import com.jva.databinding.ItemSelfSubHelpBinding


class SelfSubHelpAdapter(
    private val list: List<SelfHelpSubCategory>,
    private val onItemClick: (selfHelpCategory: SelfHelpSubCategory) -> Unit
) : RecyclerView.Adapter<SelfSubHelpAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelfSubHelpAdapter.ViewHolder {
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

    override fun onBindViewHolder(holder: SelfSubHelpAdapter.ViewHolder, position: Int) {
        holder.bindData(onItemClick, list?.get(position))
    }

    inner class ViewHolder(val binding: ItemSelfSubHelpBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            onItemClick: (serviceData: SelfHelpSubCategory) -> Unit,
            serviceData: SelfHelpSubCategory,
        ) {
            binding.title.text = serviceData.sub_name


            binding.root.setOnClickListener{
                onItemClick(serviceData)
            }
        }
    }
}