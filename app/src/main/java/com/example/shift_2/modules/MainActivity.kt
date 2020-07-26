package com.example.shift_2.modules

import CurrentWeather
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shift_2.R
import com.example.shift_2.modules.adapters.WeatherAdapter
import com.example.shift_2.network.Constants
import com.example.shift_2.network.WeatherApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var citiesWeatherList:MutableList<CurrentWeather> = mutableListOf()
    val citiesId= listOf<Int>(1489425, 5202009, 491422, 2013348, 1486209)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        for (cityId in citiesId) {
            val call: Call<CurrentWeather> =
                WeatherApiClient.apiClient.getCurrentWeatherById(Constants.API_KEY, cityId)
            call.enqueue(object : Callback<CurrentWeather> {
                override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                    Log.e("ERROR!", t.toString())
                }

                override fun onResponse(
                    call: Call<CurrentWeather>,
                    response: Response<CurrentWeather>
                ) {
                    Toast.makeText(this@MainActivity, response!!.body()?.name, Toast.LENGTH_LONG).show()

                    val cityWeather = response!!.body()
                    Log.i("cityWeather", cityWeather.toString())

                    cityWeather?.let { citiesWeatherList.add(it) }
                    check()
                }
            }
            )
        }
    }
    private fun weatherItemClicked(currentWeather: CurrentWeather) {
        ///nextSteps
    }
    private fun check() {
        if (citiesWeatherList.size==5)
        {
            for (city in citiesWeatherList) {
                Log.i("CITY", city.name)
                Log.i("CITY", citiesWeatherList.size.toString())
            }
            val recyclerView = weather_recycler_view
            recyclerView.layoutManager= LinearLayoutManager(this)
            recyclerView.adapter =
                WeatherAdapter(citiesWeatherList,
                    R.layout.list_item_weather,
                    { currentWeather: CurrentWeather -> weatherItemClicked(currentWeather) })
        }
    }
}
