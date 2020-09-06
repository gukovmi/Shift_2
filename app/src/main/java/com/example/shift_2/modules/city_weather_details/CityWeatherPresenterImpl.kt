package com.example.shift_2.modules.city_weather_details

import CurrentWeather
import com.example.shift_2.presentation.base.BasePresenter

class CityWeatherPresenterImpl: BasePresenter<CityWeatherActivity>(), CityWeatherPresenter {

    override fun showCityWeather(currentWeather: CurrentWeather) {
        view?.showCityWeather(currentWeather)
    }

    fun onViewAttached(currentWeather: CurrentWeather) {
        showCityWeather(currentWeather)
    }
}