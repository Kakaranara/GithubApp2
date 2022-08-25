package com.example.wahyugithub2.datacenter

import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.datacenter.pojo.FollowResponse
import com.example.wahyugithub2.datacenter.pojo.FollowResponseItem
import com.example.wahyugithub2.datacenter.pojo.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchData(
        @Query("q") name: String,
        @Query("per_page") limit : Int = 18
    ) : Call<SearchResponse>

    @Headers("Authorization: token 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")
    @GET("users/{name}")
    fun getDetailUser(
        @Path("name") name : String,
    ) : Call<DetailUserResponse>

    @Headers("Authorization: token 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")
    @GET("users/{name}/followers")
    fun getFollowers(
        @Path("name") name : String
    ) : Call<List<FollowResponseItem>>

    @Headers("Authorization: token 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")
    @GET("users/{name}/following")
    fun getFollowing(
        @Path("name") name : String
    ) : Call<List<FollowResponseItem>>

}