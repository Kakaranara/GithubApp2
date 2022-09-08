package com.example.wahyugithub2.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wahyugithub2.databinding.ActivityFavoriteBinding
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.datacenter.repository.FavoriteVmFactory
import com.example.wahyugithub2.datacenter.viewmodel.FavoriteViewModel
import com.example.wahyugithub2.ui.adapter.FavoriteListAdapter

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter : FavoriteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            FavoriteVmFactory.getInstance(application)
        )[FavoriteViewModel::class.java]

        setupRecv()
        observeRecyclerView()
    }

    private fun observeRecyclerView(){
        viewModel.getFavoriteData().observe(this){ favData ->
            adapter.setListFavorites(favData)
        }
    }

    private fun setupRecv(){
        adapter = FavoriteListAdapter()
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.setHasFixedSize(true)

        adapter.setOnClickListener(object : FavoriteListAdapter.OnItemCallback{
            override fun setDetailClickButton(data: DetailUserResponse) {
                Intent(this@FavoriteActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRAS, data)
                    startActivity(it)
                }
            }

            override fun setDeleteClickButton(data: DetailUserResponse) {
                viewModel.deleteData(data)
                Toast.makeText(this@FavoriteActivity, "deleted successfully", Toast.LENGTH_SHORT).show()
            }
        })

        val manager = LinearLayoutManager(this)
        binding.rvFavorite.layoutManager = manager
        val decor = DividerItemDecoration(this, manager.orientation)
        binding.rvFavorite.addItemDecoration(decor)
    }
}