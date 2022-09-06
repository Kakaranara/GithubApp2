package com.example.wahyugithub2.datacenter.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey
    val id : Int = 0
)