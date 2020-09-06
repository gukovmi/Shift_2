package com.example.shift_2.network

import CurrentWeather
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val weatherApiClient=retrofit.create(WeatherApiInterface::class.java)

    fun getCurrentWeatherById(id: Int): Single<CurrentWeather> {
        return weatherApiClient.getCurrentWeatherById(Constants.API_KEY, id)
    }

}