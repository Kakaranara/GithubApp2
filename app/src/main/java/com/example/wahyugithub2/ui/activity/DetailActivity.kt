package com.example.wahyugithub2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wahyugithub2.R
import com.example.wahyugithub2.ui.adapter.ViewPagerAdapter
import com.example.wahyugithub2.databinding.ActivityDetailBinding
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.datacenter.repository.FavoriteVmFactory
import com.example.wahyugithub2.datacenter.viewmodel.FavoriteViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var dbViewModel: FavoriteViewModel
    private lateinit var obj : DetailUserResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbViewModel = ViewModelProvider(
            this,
            FavoriteVmFactory.getInstance(application)
        ).get(FavoriteViewModel::class.java)

        obj = intent.getParcelableExtra<DetailUserResponse>(EXTRAS) as DetailUserResponse
        supportActionBar?.title = resources.getString(R.string.appbarDetail, obj.login)

        setupViewPager()
        observeFab()
        setupView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return true
    }

    private fun setupViewPager(){
        val bundle = Bundle()
        bundle.putString("username", obj.login)

        binding.viewPager.adapter = ViewPagerAdapter(this, bundle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> tab.text = "Follower"
                1 -> tab.text = "Following"
            }
        }.attach()

    }

    private fun observeFab(){
        //      id pasti tidak akan null
        dbViewModel.getExactData(obj.id!!).observe(this){
            val fab = binding.fab
            if(it == null){
                fab.setImageResource(R.drawable.ic_baseline_favorite_border_black)
                binding.fab.setOnClickListener {
                    dbViewModel.insertData(obj)
                }
            }
            else{
                binding.fab.setImageResource(R.drawable.ic_baseline_favorite_black)
                binding.fab.setOnClickListener{
                    dbViewModel.deleteData(obj)
                    finish()
                }
            }
            dbViewModel.getFavoriteData()
        }
    }

    private fun setupView(){
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