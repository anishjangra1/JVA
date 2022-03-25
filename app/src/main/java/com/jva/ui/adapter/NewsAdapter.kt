package com.jva.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jva.data.model.Dataa
import com.jva.data.model.NewsData
import com.jva.databinding.ItemKnowledgebankBinding
import com.jva.databinding.ItemNewsBinding


class NewsAdapter(
    private val list: List<NewsData>,
    private val onItemClick: (newsData: NewsData) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val binding: ItemNewsBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.bindData(onItemClick, list?.get(position))
    }

    inner class ViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            onItemClick: (newsData: NewsData) -> Unit,
            newsData: NewsData,
        ) {
            binding.newsTitle.text = newsData.title
            binding.newsDetails.text = newsData.details
            binding.newsDate.text = newsData.added_date

            binding.root.setOnClickListener{
                onItemClick(newsData)
            }
        }
    }
}