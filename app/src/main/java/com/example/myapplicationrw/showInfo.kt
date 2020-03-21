package com.example.myapplicationrw


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationrw.adapter.CurrentWeatherViewAdapter
import com.example.myapplicationrw.adapter.ForecastWeatherViewAdapter
import com.example.myapplicationrw.data.CurrentWeather
import com.example.myapplicationrw.data.CurrentWeatherGson
import com.example.myapplicationrw.data.ForecastWeather
import com.example.myapplicationrw.data.ForecastWeatherGson
import com.example.myapplicationrw.databinding.FragmentShowInfoBinding
import com.example.myapplicationrw.viewmodel.CurrentWeatherViewModel
import com.example.myapplicationrw.viewmodel.ForecastWeatherViewModel
import kotlinx.android.synthetic.main.fragment_frag_rv.view.*
import kotlinx.android.synthetic.main.fragment_show_info.view.*

/**
 * A simple [Fragment] subclass.
 */
class showInfo : Fragment(), ForecastWeatherViewAdapter.onListInteractions {

    lateinit var forecastWeather : ForecastWeather
    lateinit var binder : FragmentShowInfoBinding
    private lateinit var recyclerView: RecyclerView


    lateinit var weather: CurrentWeather
    val forecast_weathers = mutableListOf<ForecastWeather>()
    private var adapter : ForecastWeatherViewAdapter? = null
    private lateinit var viewModel: ForecastWeatherViewModel
    private var weatherList = mutableListOf<ForecastWeatherGson>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_show_info, container, false)

        viewModel = ViewModelProvider(this).get(ForecastWeatherViewModel::class.java)

        adapter = ForecastWeatherViewAdapter(
            forecast_weathers,
            this
        )

        binder.list.layoutManager = LinearLayoutManager(context)
        binder.list.adapter = adapter

        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weather = arguments!!.getParcelable("weather")!!

        recyclerView = view.list

        binder.weather = weather

        viewModel.deleteForecastWeathers()
        viewModel.addForecastWeathers(weather.id)

        loadData()
    }

    private fun loadData() {
        forecast_weathers.clear()
        viewModel.getForecastWeathers().observe(viewLifecycleOwner, Observer { obsFW ->
            run {
                val chunkList = obsFW.chunked(8)

                weatherList = obsFW as MutableList<ForecastWeatherGson>

                for(randWeather in chunkList) {
                    val dayForecast = randWeather.first()

                    val weatherForecast =dayForecast.weather.first()

                    var weather = ForecastWeather(
                        dayForecast.main.temp,
                        weatherForecast.main,
                        weatherForecast.description,
                        dayForecast.main.humidity,
                        dayForecast.dt,
                        "https://openweathermap.org/img/wn/${weatherForecast.icon}@2x.png"
                    )
                    forecast_weathers.add(weather)
                }
                adapter!!.updateData()
                recyclerView.adapter = adapter
            }
        })
    }

    override fun onListItemInteraction(item: ForecastWeather?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListButtonInteraction(item: ForecastWeather?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
