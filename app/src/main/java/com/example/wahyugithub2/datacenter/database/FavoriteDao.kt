package com.example.wahyugithub2.datacenter.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getAllData() : LiveData<List<Favorite>>
}