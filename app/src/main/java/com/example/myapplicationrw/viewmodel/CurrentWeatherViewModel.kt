package com.example.myapplicationrw.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplicationrw.dao.CurrentWeatherDao
import com.example.myapplicationrw.data.CurrentWeatherGson

class CurrentWeatherViewModel(application: Application) : AndroidViewModel(application) {

    private var currentWeatherDao : CurrentWeatherDao = CurrentWeatherDao.getInstance(this.getApplication())

    fun addWeathers() {
        currentWeatherDao.addWeathers()
    }

    fun getWeathers(): MutableLiveData<List<CurrentWeatherGson>> {
        return currentWeatherDao.getWeathers()
    }

    fun deleteCurrentWeathers(){
        currentWeatherDao.deleteCurrentWeathers()
    }
}