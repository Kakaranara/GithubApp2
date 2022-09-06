package com.example.wahyugithub2.datacenter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "data")
    val data : String? = null
)