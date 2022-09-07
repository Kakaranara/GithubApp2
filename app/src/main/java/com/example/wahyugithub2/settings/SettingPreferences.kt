package com.example.wahyugithub2.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences private constructor(private val dataStore : DataStore<Preferences>){

    private val themeSetting = booleanPreferencesKey("theme_setting")

    fun getThemeSettings() : Flow<Boolean>{
        return dataStore.data.map { flow ->
            flow[themeSetting] ?: false
        }
    }

    suspend fun saveThemeSettings(setting : Boolean){
        dataStore.edit { pref ->
            pref[themeSetting] = setting
        }
    }

    companion object{
        @Volatile
        var INSTANCE : SettingPreferences? = null

        @JvmStatic
        fun getInstance(dataStore : DataStore<Preferences>) : SettingPreferences{
            return INSTANCE ?: synchronized(this){
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }

    }

}