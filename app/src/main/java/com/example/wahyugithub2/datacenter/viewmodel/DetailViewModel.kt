package com.example.wahyugithub2.datacenter.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.datacenter.repository.FavoriteRepository

class DetailViewModel(application: Application) : ViewModel() {
    val repo = FavoriteRepository(application)

    fun getExactData(id: Int): LiveData<DetailUserResponse> = repo.getExactData(id)
    fun insertData(data: DetailUserResponse) {
        repo.insert(data)
    }

    fun deleteData(data: DetailUserResponse) {
        repo.delete(data)
    }
}