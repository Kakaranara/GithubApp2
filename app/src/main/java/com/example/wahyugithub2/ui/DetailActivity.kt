package com.example.wahyugithub2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.wahyugithub2.R
import com.example.wahyugithub2.ui.adapter.ViewPagerAdapter
import com.example.wahyugithub2.databinding.ActivityDetailBinding
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val obj = intent.getParcelableExtra<DetailUserResponse>(EXTRAS) as DetailUserResponse
        supportActionBar?.title = resources.getString(R.string.appbarDetail, obj.login)

        val bundle = Bundle()
        bundle.putString("username", obj.login)

        binding.viewPager.adapter = ViewPagerAdapter(this, bundle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> tab.text = "Follower"
                1 -> tab.text = "Following"
            }
        }.attach()

        with(binding) {
            tvCompany.text = obj.company ?: resources.getString(R.string.no_company)
            tvDetailLocation.text = obj.location ?: resources.getString(R.string.no_location)
            tvDetailName.text = obj.name ?: resources.getString(R.string.anon)
            tvFollower.text = resources.getString(R.string.followerTemplate, obj.followers ?: 0)
            tvFollowing.text = resources.getString(R.string.followingTemplate, obj.following ?: 0)
        }
        Glide.with(this)
            .load(obj.avatarUrl)
            .into(binding.photoDetail)
    }

    companion object {
        const val EXTRAS = "value"
    }
}