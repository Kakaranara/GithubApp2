package com.example.wahyugithub2.settings

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SettingsViewModel(private val preferences: SettingPreferences) : ViewModel() {
    fun getTheme() : LiveData<Boolean>{
        return preferences.getThemeSettings().asLiveData()
    }

    fun setDarkMode(isDarkMode: Boolean){
        viewModelScope.launch {
            preferences.saveThemeSettings(isDarkMode)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class VmPreferenceFactory(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingsViewModel::class.java)){
            return SettingsViewModel(pref) as T
        }
        return super.create(modelClass)
    }
}