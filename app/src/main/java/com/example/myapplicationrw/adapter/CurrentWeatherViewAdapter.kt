package com.example.myapplicationrw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationrw.R
import com.example.myapplicationrw.data.CurrentWeather
import com.example.myapplicationrw.util.StringUtil.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*
import androidx.databinding.DataBindingUtil
import com.example.myapplicationrw.databinding.RowBinding

class CurrentWeatherViewAdapter(private val mValue: List<CurrentWeather>, private val mListener : onListInteractions) : RecyclerView.Adapter<CurrentWeatherViewAdapter.viewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        val view: RowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValue.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = mValue[position]
        holder.mView.weather = item
        holder.mView.executePendingBindings()

        holder.mView.cardWeather.setOnClickListener {
            mListener.onListButtonInteraction(item)
        }

        /*holder.text_city.text = item.city
        holder.text_description.text = item.descriptionWeather.capitalize()
        holder.text_temp.text = formatTemperature(item.currentWeather)
        Picasso.get().load(item.thumbnail).resize(20, 20).centerCrop().into(holder.image_clima)
        holder.time.text = formatTime(item.dt)
        holder.humidity.text = formatHumidity(item.humidity)

        holder.mView.setOnClickListener{
            mListener?.onListItemInteraction(item)
        }

        holder.card_view.setOnClickListener{
            mListener?.onListButtonInteraction(item)
        } */
    }

    inner class viewHolder(val mView: RowBinding) : RecyclerView.ViewHolder(mView.root) {

        /*val card_view : CardView = mView.findViewById(R.id.cardWeather)
        val text_city : TextView = card_view.city
        val text_description : TextView = card_view.main_weather
        val text_temp : TextView = card_view.current_weather
        val image_clima : ImageView = card_view.thumbnail
        val time :TextView = card_view.time
        val humidity : TextView = card_view.humidity */
    }

    interface  onListInteractions {
        fun onListItemInteraction(item: CurrentWeather?)

        fun onListButtonInteraction(item: CurrentWeather?)
    }

    public fun updateData() {
        notifyDataSetChanged()
    }
}