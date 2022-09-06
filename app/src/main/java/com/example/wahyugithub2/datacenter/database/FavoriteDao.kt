package com.example.wahyugithub2.datacenter.database

import androidx.room.*


@Dao
interface FavoriteDao {
    @Insert
    fun insert()

}