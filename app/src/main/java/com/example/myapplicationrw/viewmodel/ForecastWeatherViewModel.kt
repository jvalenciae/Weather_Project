package com.example.myapplicationrw.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplicationrw.dao.ForecastWeatherDao
import com.example.myapplicationrw.data.ForecastWeatherGson

class ForecastWeatherViewModel(application: Application) : AndroidViewModel(application) {

    private var forecastWeatherDao : ForecastWeatherDao = ForecastWeatherDao.getInstance(this.getApplication())

    fun addForecastWeathers(id: String) {
        forecastWeatherDao.addForecastWeathers(id)
    }

    fun getForecastWeathers(): MutableLiveData<List<ForecastWeatherGson>> {
        return forecastWeatherDao.getForecastWeathers()
    }

    fun deleteForecastWeathers(){
        forecastWeatherDao.deleteForecastWeathers()
    }
}