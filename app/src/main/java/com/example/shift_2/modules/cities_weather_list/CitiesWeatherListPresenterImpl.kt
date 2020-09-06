package com.example.shift_2.modules.cities_weather_list

import CurrentWeather
import android.util.Log
import com.example.shift_2.data.Cities
import com.example.shift_2.network.WeatherApiClient
import com.example.shift_2.presentation.base.BasePresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function5
import io.reactivex.schedulers.Schedulers

class CitiesWeatherListPresenterImpl: BasePresenter<CitiesWeatherListActivity>(), CitiesWeatherListPresenter {

    override fun onViewAttached() {
        getCitiesWeatherList(Cities.citiesId)
    }

    override fun getCitiesWeatherList(citiesId: List<Int>) {
        Single
            .zip(
                WeatherApiClient.getCurrentWeatherById(Cities.citiesId[0]),
                WeatherApiClient.getCurrentWeatherById(Cities.citiesId[1]),
                WeatherApiClient.getCurrentWeatherById(Cities.citiesId[2]),
                WeatherApiClient.getCurrentWeatherById(Cities.citiesId[3]),
                WeatherApiClient.getCurrentWeatherById(Cities.citiesId[4]),
                Function5<CurrentWeather, CurrentWeather, CurrentWeather, CurrentWeather, CurrentWeather, MutableList<CurrentWeather>>
                {
                        it1, it2, it3, it4, it5 -> mutableListOf(it1, it2, it3, it4, it5)
                }
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    showCitiesWeatherList(it)
                },{
                    Log.e("ERROR!!", it.localizedMessage)
                }
            ).untilDestroy()
    }

    private fun showCitiesWeatherList(citiesWeatherList: MutableList<CurrentWeather>) {
        view?.showCitiesWeatherList(citiesWeatherList)
    }

}