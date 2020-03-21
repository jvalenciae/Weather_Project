package com.example.myapplicationrw


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.myapplicationrw.adapter.CurrentWeatherViewAdapter
import com.example.myapplicationrw.data.CurrentWeather
import com.example.myapplicationrw.data.CurrentWeatherGson
import com.example.myapplicationrw.viewmodel.CurrentWeatherViewModel
import kotlinx.android.synthetic.main.fragment_frag_rv.view.*
import kotlinx.android.synthetic.main.fragment_frag_rv.*

/**
 * A simple [Fragment] subclass.
 */
class frag_rv : Fragment(), CurrentWeatherViewAdapter.onListInteractions {

    val weathers = mutableListOf<CurrentWeather>()
    private var adapter : CurrentWeatherViewAdapter? = null
    lateinit var navController: NavController
    private lateinit var viewModel: CurrentWeatherViewModel
    private var weatherList = mutableListOf<CurrentWeatherGson>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_frag_rv, container, false)

        recyclerView = view.forecastList

        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)

        adapter = CurrentWeatherViewAdapter(
            weathers,
            this
        )

        viewModel.deleteCurrentWeathers()
        viewModel.addWeathers()

        loadData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

    }

    override fun onListItemInteraction(item: CurrentWeather?) {

    }

    override fun onListButtonInteraction(item: CurrentWeather?) {
        val bundle = bundleOf("data" to item,"weather" to item)
        navController!!.navigate(R.id.action_frag_rv_to_showInfo, bundle)
        println("click " + item!!.city)
    }

    private fun loadData() {
        weathers.clear()

        viewModel.getWeathers().observe(viewLifecycleOwner, Observer { obsCW ->
            run {
                weatherList = obsCW as MutableList<CurrentWeatherGson>

                for(randWeather in weatherList) {
                    val current = randWeather.weather.first()
                    var weather = CurrentWeather(
                        randWeather.id.toString(),
                        randWeather.name,
                        randWeather.main.temp,
                        current.main,
                        current.description,
                        randWeather.main.humidity,
                        randWeather.dt,
                        "https://openweathermap.org/img/wn/${current.icon}@2x.png"
                    )
                    weathers.add(weather)
                }
                adapter!!.updateData()
            }
        })
    }


    fun getStringRequest() : StringRequest {
        val url = "https://api.openweathermap.org/data/2.5/group?id=3688685,3674962&units=metric&lang=es&appid=efcf89f950693d9e13a559215ad75a3b"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                textViewrv.text = response.toString()
                println(response)
            },
            Response.ErrorListener {
                println("error")
            }
        )

        return stringRequest
    }


}
