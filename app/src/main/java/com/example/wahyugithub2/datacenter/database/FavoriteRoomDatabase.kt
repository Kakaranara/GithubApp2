package com.example.wahyugithub2.datacenter.database

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.*

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
}