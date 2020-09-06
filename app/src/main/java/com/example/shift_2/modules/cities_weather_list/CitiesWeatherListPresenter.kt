package com.example.shift_2.modules.cities_weather_list

interface CitiesWeatherListPresenter {
    fun getCitiesWeatherList(citiesId: List<Int>)
    fun onViewAttached()
}