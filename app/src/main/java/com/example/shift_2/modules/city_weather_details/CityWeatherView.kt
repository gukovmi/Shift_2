package com.example.shift_2.modules.city_weather_details

import CurrentWeather

interface CityWeatherView {
    fun showCityWeather(currentWeather: CurrentWeather)
}