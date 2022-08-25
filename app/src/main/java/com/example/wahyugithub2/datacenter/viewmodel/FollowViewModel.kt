package com.example.wahyugithub2.datacenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wahyugithub2.datacenter.ApiConfig
import com.example.wahyugithub2.datacenter.pojo.FollowResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {
    private val _dataFollower = MutableLiveData<List<FollowResponseItem>>()
    val dataFollower : LiveData<List<FollowResponseItem>> = _dataFollower

    private val _dataFollowing = MutableLiveData<List<FollowResponseItem>>()
    val dataFollowing : LiveData<List<FollowResponseItem>> = _dataFollowing

    private val _isFollowingLoading = MutableLiveData<Boolean>()
    val isFollowingLoading : LiveData<Boolean> = _isFollowingLoading

    private val _isFollowerLoading = MutableLiveData<Boolean>()
    val isFollowerLoading : LiveData<Boolean> = _isFollowerLoading

    private val _followerErrorMsg = MutableLiveData<Event<String>>()
    val followerErrorMsg : LiveData<Event<String>> = _followerErrorMsg

    private val _followingErrorMsg = MutableLiveData<Event<String>>()
    val followingrErrorMsg : LiveData<Event<String>> = _followerErrorMsg


    fun getFollower(path : String){
        _isFollowerLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(path)
        client.enqueue(object : Callback<List<FollowResponseItem>>{
            override fun onResponse(
                call: Call<List<FollowResponseItem>>,
                response: Response<List<FollowResponseItem>>
            ) {
                if(response.isSuccessful){
                    _isFollowerLoading.value = false
                    _dataFollower.value = response.body()
                }else{
                    _followerErrorMsg.value = errorMsg()
                }
            }

            override fun onFailure(call: Call<List<FollowResponseItem>>, t: Throwable) {
                _isFollowerLoading.value = false
                _followerErrorMsg.value = errorMsg()
            }
        })

    }

    fun getFollowing(path : String){
        _isFollowingLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(path)
        client.enqueue(object : Callback<List<FollowResponseItem>>{
            override fun onResponse(
                call: Call<List<FollowResponseItem>>,
                response: Response<List<FollowResponseItem>>
            ) {
                if(response.isSuccessful){
                    _isFollowingLoading.value = false
                    _dataFollowing.value = response.body()
                }else{
                    _followingErrorMsg.value = errorMsg()
                }
            }

            override fun onFailure(call: Call<List<FollowResponseItem>>, t: Throwable) {
                _isFollowingLoading.value = false
                _followingErrorMsg.value = errorMsg()
            }
        })
    }

    fun errorMsg() : Event<String> = Event("Terjadi suatu error")

}