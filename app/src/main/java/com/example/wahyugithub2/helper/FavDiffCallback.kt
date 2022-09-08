package com.example.wahyugithub2.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse

class FavDiffCallback(private val oldList: List<DetailUserResponse>, private val newList: List<DetailUserResponse> ) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        return old.login == new.login
    }
}