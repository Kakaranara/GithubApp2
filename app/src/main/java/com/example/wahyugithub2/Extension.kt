package com.example.wahyugithub2

import android.content.Context
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

fun View.showLoading(isLoading : Boolean){
    this.visibility = when(isLoading){
        true -> View.VISIBLE
        false -> View.INVISIBLE
    }
}

val Context.settings : DataStore<Preferences> by preferencesDataStore("settings")
