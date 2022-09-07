package com.example.wahyugithub2.datacenter.database

import android.content.Context
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.*
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse

@Database(entities = [DetailUserResponse::class], version = 1)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE : FavoriteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context : Context) : FavoriteRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteRoomDatabase::class.java,
                    "favoriteDb"
                ).build()

                instance
            }
        }
    }
}