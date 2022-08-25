package com.example.wahyugithub2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wahyugithub2.databinding.ListFollowBinding
import com.example.wahyugithub2.pojo.FollowResponseItem

class FollowListAdapter(private val list: List<FollowResponseItem>) :
    RecyclerView.Adapter<FollowListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ListFollowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListFollowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.binding.tvFollowName.text = data.login
        Glide.with(holder.itemView.context)
            .load(data.avatarUrl)
            .into(holder.binding.imgFollow)
    }

    override fun getItemCount(): Int = list.size
}