package com.example.myapplicationrw.dao

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.myapplicationrw.VolleySingleton
import com.example.myapplicationrw.data.ForecastWeatherGson
import org.json.JSONObject

class ForecastWeatherDao private constructor(var context: Context) {

    private val forecast_weathers = MutableLiveData<List<ForecastWeatherGson>>()
    private val forecast_weatherList = mutableListOf<ForecastWeatherGson>()
    private var queue: RequestQueue

    init{
        queue = VolleySingleton.getInstance(
            context
        ).requestQueue
    }

    companion object{
        @Volatile
        private var INSTANCE: ForecastWeatherDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE
                    ?: ForecastWeatherDao(context).also { INSTANCE = it }
            }
    }

    fun addForecastWeathers(id: String) {
        VolleySingleton.getInstance(context)
            .addToRequestQueue(getJsonObject(id))
    }

    fun getForecastWeathers() = forecast_weathers

    fun deleteForecastWeathers(){
        forecast_weatherList.clear()
    }

    fun getJsonObject(id: String): JsonObjectRequest{
        val url = "https://api.openweathermap.org/data/2.5/forecast?id=${id}&units=metric&lang=es&appid=efcf89f950693d9e13a559215ad75a3b"
        /*Bogotá - Medellín - Cali - Barranquilla - Cartagena - Cúcuta - Bucaramanga - Soledad - Ibagué - Pereira  */
        /*3688685 - 3674962 - 3687925 - 3689147 - 3687238 - 3685533 - 3688465 - 3667849 - 3680656 - 3672486 */
        /* API Key: efcf89f950693d9e13a559215ad75a3b */

        return JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                parseObjectG(response)
            },
            Response.ErrorListener { error->
                Log.d("WebRequestTest", "That didn't work ${error.message}")
            }
        )
    }

    private fun parseObjectG(response: JSONObject) {
        var list = ForecastWeatherGson.getClimateForecast(response)
        val i: Int = 0
        val size: Int = list.size
        for (i in 0 until size) {
            val weather = list[i]
            forecast_weatherList.add(weather)
        }
        forecast_weathers.value = forecast_weatherList
    }

}