package com.jva.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jva.R
import com.jva.data.model.Dataa
import com.jva.data.model.NewsData
import com.jva.data.model.ServiceData
import com.jva.databinding.ItemKnowledgebankBinding
import com.jva.databinding.ItemNewsBinding
import com.jva.databinding.ItemServicesBinding


class ServicesAdapter(
    private val context: Context,
    private val list: List<ServiceData>,
    private val onItemClick: (serviceData: ServiceData) -> Unit
) : RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesAdapter.ViewHolder {
        val binding: ItemServicesBinding = ItemServicesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ServicesAdapter.ViewHolder, position: Int) {
        holder.bindData(onItemClick, list?.get(position))
    }

    inner class ViewHolder(val binding: ItemServicesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            onItemClick: (serviceData: ServiceData) -> Unit,
            serviceData: ServiceData,
        ) {
            Glide.with(context)
                .load(serviceData.images) // image url
                .placeholder(R.drawable.logo_j) // any placeholder to load at start
                .error(R.drawable.logo_j)  // any image in case of error
                .override(70, 70) // resizing
                .centerCrop()
                .into(binding.ivlogo);
            binding.title.text = serviceData.product_title


            binding.root.setOnClickListener{
                onItemClick(serviceData)
            }
        }
    }
}