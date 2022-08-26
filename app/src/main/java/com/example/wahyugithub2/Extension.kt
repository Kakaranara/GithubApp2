package com.example.wahyugithub2

import android.view.View

fun View.showLoading(isLoading : Boolean){
    this.visibility = when(isLoading){
        true -> View.VISIBLE
        false -> View.INVISIBLE
    }
}