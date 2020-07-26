package com.example.shift_2.network

import CurrentWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {

    @GET("weather")
    fun getCurrentWeatherById(
        @Query("appid") apiKey:String,
        @Query("id") cityId:Int
    ): retrofit2.Call<CurrentWeather>


}