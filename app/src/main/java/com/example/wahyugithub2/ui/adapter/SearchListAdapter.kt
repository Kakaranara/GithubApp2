package com.example.wahyugithub2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wahyugithub2.R
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.databinding.ListItemBinding

class SearchListAdapter(private val list: List<DetailUserResponse>) :
    RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    private lateinit var listener : OnItemCallbackListener

    fun setOnItemCallbackListener(listener : OnItemCallbackListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        with(holder) {
            with(binding) {
                val res = holder.itemView.resources
                tvName.text = data.name ?: res.getString(R.string.anon)
                tvUsername.text = data.login ?: res.getString(R.string.no_username)
                tvLocation.text = data.location ?: res.getString(R.string.no_location)
                btnDetail.setOnClickListener {
                    listener.setOnItemCallbackListener(data)
                }
            }
        }
        Glide.with(holder.itemView.context)
            .load(data.avatarUrl)
            .centerCrop()
            .into(holder.binding.imgProfile)
    }

    override fun getItemCount() = list.size

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemCallbackListener{
        fun setOnItemCallbackListener(data : DetailUserResponse)
    }

}