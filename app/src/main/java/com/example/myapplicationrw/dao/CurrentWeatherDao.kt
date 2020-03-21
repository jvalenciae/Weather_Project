package com.example.myapplicationrw.dao

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.myapplicationrw.VolleySingleton
import com.example.myapplicationrw.data.CurrentWeatherGson
import org.json.JSONObject

class CurrentWeatherDao private constructor(var context: Context) {

    private val weathers = MutableLiveData<List<CurrentWeatherGson>>()
    private val weatherList = mutableListOf<CurrentWeatherGson>()
    private var queue: RequestQueue

    init{
        queue = VolleySingleton.getInstance(
            context
        ).requestQueue
    }

    companion object{
        @Volatile
        private var INSTANCE: CurrentWeatherDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE
                    ?: CurrentWeatherDao(context).also { INSTANCE = it }
            }
    }

    fun addWeathers() {
        VolleySingleton.getInstance(context)
            .addToRequestQueue(getJsonObject())
    }

    fun getWeathers() = weathers

    fun deleteCurrentWeathers(){
        weatherList.clear()
    }

    fun getJsonObject(): JsonObjectRequest{
        val url = "https://api.openweathermap.org/data/2.5/group?id=3688685,3674962,3687925,3689147,3687238,3685533,3688465,3667849,3680656,3672486&units=metric&lang=es&appid=efcf89f950693d9e13a559215ad75a3b"
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
        var list = CurrentWeatherGson.getCurrentWeather(response)
        val i: Int = 0
        val size: Int = list.size
        for (i in 0 until size) {
            val weather = list[i]
            weatherList.add(weather)
        }
        weathers.value = weatherList
    }

}