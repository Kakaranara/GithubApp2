package com.example.wahyugithub2.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wahyugithub2.R
import com.example.wahyugithub2.ui.adapter.SearchListAdapter
import com.example.wahyugithub2.datacenter.viewmodel.SearchViewModel
import com.example.wahyugithub2.databinding.ActivityMainBinding
import com.example.wahyugithub2.datacenter.pojo.DetailUserResponse
import com.example.wahyugithub2.settings.SettingPreferences
import com.example.wahyugithub2.settings.SettingsViewModel
import com.example.wahyugithub2.settings.VmPreferenceFactory
import com.example.wahyugithub2.showLoading

val Context.settings : DataStore<Preferences> by preferencesDataStore("settings")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        checkSettings()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        viewModel.isLoading.observe(this) { binding.progressBar.showLoading(it) }
        viewModel.isEmpty.observe(this){ showEmpty(it) }
        observeRecyclerView()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_setting -> {
                Intent(this, SettingsActivity::class.java).also {
                    startActivity(it)
                }
            }
        }

        return true
    }

    private fun checkSettings(){
        val pref = SettingPreferences.getInstance(settings)
        val viewModel = ViewModelProvider(this, VmPreferenceFactory(pref)).get(SettingsViewModel::class.java)

        viewModel.getTheme().observe(this){
            if(it) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun observeRecyclerView(){
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