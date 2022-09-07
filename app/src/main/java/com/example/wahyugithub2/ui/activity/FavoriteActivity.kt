package com.example.wahyugithub2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wahyugithub2.databinding.ActivityFavoriteBinding
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.datacenter.repository.FavoriteVmFactory
import com.example.wahyugithub2.datacenter.viewmodel.FavoriteViewModel
import com.example.wahyugithub2.ui.adapter.SearchListAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            FavoriteVmFactory(application)
        )[FavoriteViewModel::class.java]

        observeRecyclerView()
    }

    private fun observeRecyclerView(){
        viewModel.getFavoriteData().observe(this){
            val adapter = SearchListAdapter(it)
            binding.rvFavorite.adapter = adapter

            adapter.setOnItemCallbackListener(object : SearchListAdapter.OnItemCallbackListener{
                override fun setOnItemCallbackListener(data: DetailUserResponse) {
                    Intent(this@FavoriteActivity, DetailActivity::class.java).also{
                        it.putExtra(DetailActivity.EXTRAS, data)
                        startActivity(it)
                    }
                }
            })
        }


        val manager = LinearLayoutManager(this)
        binding.rvFavorite.layoutManager = manager
        val decor = DividerItemDecoration(this, manager.orientation)
        binding.rvFavorite.addItemDecoration(decor)
    }
}