package com.example.wahyugithub2.datacenter.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: DetailUserResponse)

    @Delete
    fun delete(favorite: DetailUserResponse)

    @Query("SELECT * FROM detailuserresponse WHERE id = :ids")
    fun getExactData(ids : Int) : LiveData<DetailUserResponse>

    @Query("SELECT * FROM detailuserresponse ORDER BY id ASC")
    fun getAllData() : LiveData<List<DetailUserResponse>>
}