package com.example.shift_2.modules.cities_weather_list

import CurrentWeather

interface CitiesWeatherListView {
    fun showCitiesWeatherList(citiesWeatherList: MutableList<CurrentWeather>)
}