package com.example.wahyugithub2.datacenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wahyugithub2.datacenter.ApiConfig
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.datacenter.pojo.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _listUserDetail = MutableLiveData<List<DetailUserResponse>>()
    val listUserDetail: LiveData<List<DetailUserResponse>> = _listUserDetail

    fun searchUser(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchData(name)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val body = response.body()
                if(response.isSuccessful){
                    if (body != null) {
                        if (body.items.isEmpty()) {
                            _isEmpty.value = true
                            _isLoading.value = false
                        } else {
                            findDetailUser(body)
                            _isEmpty.value = false
                            _isLoading.value = false
                        }
                    }
                }
                else{
                    _isEmpty.value = true
                    _isLoading.value = false
                }

            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }

    private fun findDetailUser(data: SearchResponse) {
        _isLoading.value = true

        val list = mutableListOf<DetailUserResponse>()
        for (items in data.items) {
            val client = ApiConfig.getApiService().getDetailUser(items.login)
            client.enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    val body = response.body()
                    Log.d("TAG", "onResponse: :$body")
                    if (response.isSuccessful) {
                        list.add(body!!)
                    }
                    _listUserDetail.value = list.toList()
                    _isLoading.value = false
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {

                }
            })
        }

    }

}