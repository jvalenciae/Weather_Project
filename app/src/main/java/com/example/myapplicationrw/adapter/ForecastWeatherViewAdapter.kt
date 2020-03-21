package com.example.myapplicationrw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationrw.R
import com.example.myapplicationrw.data.CurrentWeather
import com.example.myapplicationrw.data.ForecastWeather
import com.example.myapplicationrw.databinding.RowForecastBinding
import com.example.myapplicationrw.util.StringUtil.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_frag_rv.view.*
import kotlinx.android.synthetic.main.row_forecast.view.*
import kotlinx.android.synthetic.main.row_forecast.view.current_weather
import kotlinx.android.synthetic.main.row_forecast.view.humidity
import kotlinx.android.synthetic.main.row_forecast.view.main_weather
import kotlinx.android.synthetic.main.row_forecast.view.thumbnail
import kotlinx.android.synthetic.main.row_forecast.view.time
import kotlinx.android.synthetic.main.row_forecast.view.*

class ForecastWeatherViewAdapter(private val mValue: List<ForecastWeather>, private val mListener : onListInteractions) : RecyclerView.Adapter<ForecastWeatherViewAdapter.viewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.row_forecast, parent, false)
        val view: RowForecastBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_forecast, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValue.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = mValue[position]

        holder.mView.forecast =item
        holder.mView.executePendingBindings()

        /*holder.text_day.text = formatDate(item.dt)
        holder.text_description.text = item.descriptionWeather.capitalize()
        holder.text_temp.text = formatTemperature(item.expectedWeather)
        Picasso.get().load(item.thumbnail).resize(20,20).centerCrop().into(holder.image_clima)
        holder.time.text = formatTime(item.dt)
        holder.humidity.text = formatHumidity(item.humidity)

        holder.mView.setOnClickListener{
            mListener?.onListItemInteraction(item)
        }

        holder.card_view.setOnClickListener{
            mListener?.onListButtonInteraction(item)
        } */
    }

    inner class viewHolder(val mView: RowForecastBinding) : RecyclerView.ViewHolder(mView.root) {


        /*val card_view : CardView = mView.findViewById(R.id.cardWeatherForecast)
        val text_day : TextView = card_view.day_forecast
        val text_description : TextView = card_view.main_weather
        val text_temp : TextView = card_view.current_weather
        val image_clima : ImageView = card_view.thumbnail
        val time :TextView = card_view.time
        val humidity : TextView = card_view.humidity */
    }

    interface  onListInteractions {
        fun onListItemInteraction(item: ForecastWeather?)

        fun onListButtonInteraction(item: ForecastWeather?)
    }

    public fun updateData() {
        notifyDataSetChanged()
    }
}