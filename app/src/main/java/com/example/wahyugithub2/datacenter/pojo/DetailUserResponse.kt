package com.example.wahyugithub2.datacenter.pojo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class DetailUserResponse(


	@PrimaryKey
	@ColumnInfo(name = "id")
	@field:SerializedName("id")
	val id: Int? = null,

	@ColumnInfo(name = "login")
	@field:SerializedName("login")
	val login: String? = null,

	@ColumnInfo(name = "company")
	@field:SerializedName("company")
	val company: String? = null,

	@ColumnInfo(name = "publicRepos")
	@field:SerializedName("public_repos")
	val publicRepos: Int? = null,

	@ColumnInfo(name = "email")
	@field:SerializedName("email")
	val email: String? = null,

	@ColumnInfo(name = "followers")
	@field:SerializedName("followers")
	val followers: Int? = null,

	@ColumnInfo(name = "avatarUrl")
	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@ColumnInfo(name = "following")
	@field:SerializedName("following")
	val following: Int? = null,

	@ColumnInfo(name = "name")
	@field:SerializedName("name")
	val name: String? = null,

	@ColumnInfo(name = "location")
	@field:SerializedName("location")
	val location: String? = null,

) : Parcelable
