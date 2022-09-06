package com.example.wahyugithub2.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wahyugithub2.ui.adapter.SearchListAdapter
import com.example.wahyugithub2.datacenter.viewmodel.SearchViewModel
import com.example.wahyugithub2.databinding.ActivityMainBinding
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.showLoading

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        viewModel.isLoading.observe(this) {
            binding.progressBar.showLoading(it)
        }

        viewModel.isEmpty.observe(this){
            showEmpty(it)
        }

        viewModel.listUserDetail.observe(this) {
            val adapter = SearchListAdapter(it)
            binding.rvSearch.adapter = adapter

            adapter.setOnItemCallbackListener(object : SearchListAdapter.OnItemCallbackListener{
                override fun setOnItemCallbackListener(data: DetailUserResponse) {
                    Intent(this@MainActivity, DetailActivity::class.java).also { intent ->
                        intent.putExtra(DetailActivity.EXTRAS, data)
                        startActivity(intent)
                    }
                }
            })
        }

        val edit = binding.textInputLayout.editText
        edit?.setOnEditorActionListener { textView, _, _ ->
            viewModel.searchUser(textView.text.toString())
            false
        }

        val manager = LinearLayoutManager(this)
        binding.rvSearch.layoutManager = manager
        val decor = DividerItemDecoration(this, manager.orientation)
        binding.rvSearch.addItemDecoration(decor)

    }

    private fun showEmpty(isEmpty: Boolean) {
        binding.noDataFound.visibility = when (isEmpty) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
        val adapter = SearchListAdapter(listOf<DetailUserResponse>())
        binding.rvSearch.adapter = adapter
    }
}