package com.example.shift_2.modules.cities_weather_list

import CurrentWeather
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_weather.view.*
import java.math.BigDecimal

class CitiesWeatherListAdapter (private val citiesWeatherlist: List<CurrentWeather>,
                                private val rowLayout: Int,
                                val clickListener: (CurrentWeather) -> Unit
): RecyclerView.Adapter<CitiesWeatherListAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(v: View): RecyclerView.ViewHolder(v) {

        fun bindMovie(currentWeather: CurrentWeather, clickListener: (CurrentWeather) -> Unit) {
            itemView.apply {
                cityName.text="City: "+currentWeather.name
                val currentTemperature = currentWeather.main.temp

                cityTemperature.text="Temperature: " +
                        (BigDecimal(currentTemperature-273).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()+" C")

                cityWindSpeed.text="Wind speed: " + currentWeather.wind.speed.toString()+ " m/s"
                setOnClickListener { clickListener(currentWeather) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return WeatherViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return citiesWeatherlist.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindMovie(currentWeather= citiesWeatherlist[position], clickListener = clickListener)
    }
}