package com.example.wahyugithub2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.wahyugithub2.databinding.ActivitySettingsBinding
import com.example.wahyugithub2.settings.SettingPreferences
import com.example.wahyugithub2.settings.SettingsViewModel
import com.example.wahyugithub2.settings.VmPreferenceFactory


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = SettingPreferences.getInstance(settings)
        val viewModel : SettingsViewModel =
            ViewModelProvider(this, VmPreferenceFactory(preferences))[SettingsViewModel::class.java]

        viewModel.getTheme().observe(this){ darkMode ->
            if(darkMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switch1.isChecked = true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switch1.isChecked = false
            }
        }

        binding.switch1.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setDarkMode(isChecked)
        }

    }
}