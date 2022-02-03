package com.jva.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jva.R
import com.jva.data.model.User
import com.jva.databinding.ItemLayoutBinding

class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val user = users[position]
            holder.binding.apply {
                 textViewUserName.text = user.name
                 textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)

        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class MainViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}

