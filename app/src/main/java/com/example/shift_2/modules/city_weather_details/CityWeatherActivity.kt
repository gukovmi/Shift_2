package com.example.shift_2.modules.city_weather_details

import CurrentWeather
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shift_2.R
import com.example.shift_2.presentation.base.BaseView
import kotlinx.android.synthetic.main.activity_city_weather_details.*
import java.math.BigDecimal

class CityWeatherActivity : AppCompatActivity(), BaseView, CityWeatherView {

    private val presenter=CityWeatherPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather_details)

        val currentWeather = intent.getSerializableExtra("currentWeather") as CurrentWeather
        presenter.attachView(this)
        presenter.onViewAttached(currentWeather)
    }

    override fun showCityWeather(currentWeather: CurrentWeather) {
        weatherDetailsCityName.text="City: "+ currentWeather.name
        val currentTemperature = currentWeather.main.temp
        weatherDetailsCityTemp.text="Temperature: " +
                (BigDecimal(currentTemperature-273).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()+" C")
        weatherDetailsCityWindSpeed.text="Wind speed: " + currentWeather.wind.speed.toString()+ " m/s"
        weatherDetailsCityClouds.text="Clouds: "+currentWeather.clouds.all.toString()+ "/100%"
        weatherDetailsCityDescription.text="Descriprtion: "+currentWeather.weather.first().description
    }
}