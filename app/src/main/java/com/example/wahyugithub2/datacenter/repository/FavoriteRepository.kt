package com.example.wahyugithub2.datacenter.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wahyugithub2.datacenter.database.FavoriteDao
import com.example.wahyugithub2.datacenter.database.FavoriteRoomDatabase
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.datacenter.viewmodel.FavoriteViewModel
import java.util.concurrent.Executors

class FavoriteRepository(application : Application) {
    private val favoriteDao  : FavoriteDao
    private var executor = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application.applicationContext)
        favoriteDao = db.favoriteDao()
    }

    fun getFavoriteData() : LiveData<List<DetailUserResponse>> = favoriteDao.getAllData()

    fun getExactData(id: Int) : LiveData<DetailUserResponse> = favoriteDao.getExactData(id)

    fun insert(data : DetailUserResponse){
        executor.execute {
            favoriteDao.insert(data)
        }
    }

    fun delete(data: DetailUserResponse){
        executor.execute {
            favoriteDao.delete(data)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class FavoriteVmFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(application) as T
        }
        return super.create(modelClass)
    }

    companion object{

        @Volatile
        var INSTANCE : FavoriteVmFactory? = null

        fun getInstance(application: Application) : FavoriteVmFactory{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: FavoriteVmFactory(application).also { INSTANCE = it }
            }
        }
    }
}