package com.example.wahyugithub2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wahyugithub2.R
import com.example.wahyugithub2.databinding.ListFavoriteBinding
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.helper.FavDiffCallback

class FavoriteListAdapter() : RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    private lateinit var listener : OnItemCallback
    val list =  ArrayList<DetailUserResponse>()

    fun setListFavorites(newList: List<DetailUserResponse>){
        val diffCallback = FavDiffCallback(this.list, newList)
        val result = DiffUtil.calculateDiff(diffCallback)
        this.list.clear()
        this.list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    fun setOnClickListener(listener : OnItemCallback){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = ListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        with(holder){
            with(binding){
                nickname.text = data.name ?: holder.itemView.resources.getString(R.string.anon)
                btnFavDetail.setOnClickListener{
                    listener.setDetailClickButton(data)
                }
                btnDelete.setOnClickListener {
                    listener.setDeleteClickButton(data)
                }
            }
        }
        Glide.with(holder.itemView.context)
            .load(data.avatarUrl)
            .into(holder.binding.favImg)
    }

    override fun getItemCount(): Int = list.size
    class ViewHolder(val binding: ListFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemCallback{
        fun setDetailClickButton(data: DetailUserResponse)
        fun setDeleteClickButton(data: DetailUserResponse)
    }
}