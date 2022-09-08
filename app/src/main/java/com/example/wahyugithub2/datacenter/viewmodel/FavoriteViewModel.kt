package com.example.wahyugithub2.datacenter.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.datacenter.repository.FavoriteRepository

class FavoriteViewModel(application: Application) : ViewModel() {

    private val repo = FavoriteRepository(application)

    fun getFavoriteData() : LiveData<List<DetailUserResponse>> = repo.getFavoriteData()

    fun deleteData(data: DetailUserResponse){
        repo.delete(data)
    }
}
