package com.example.shift_2.modules.cities_weather_list

import CurrentWeather
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shift_2.R
import com.example.shift_2.data.Cities
import com.example.shift_2.modules.city_weather_details.CityWeatherActivity
import com.example.shift_2.network.Constants
import com.example.shift_2.network.WeatherApiClient
import kotlinx.android.synthetic.main.activity_cities_weather_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitiesWeatherListActivity : AppCompatActivity() {
    var citiesWeatherList:MutableList<CurrentWeather> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_weather_list)

        for (cityId in Cities.citiesId) {
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
                    val cityWeather = response.body()

                    cityWeather?.let { citiesWeatherList.add(it) }
                    check()
                }
            })
        }
    }

    private fun weatherItemClicked(currentWeather: CurrentWeather) {
        val intent= Intent(this, CityWeatherActivity::class.java)
        intent.putExtra("currentWeather", currentWeather)
        startActivity(intent)
    }

    private fun check() {
        if (citiesWeatherList.size==Cities.citiesId.size)
        {
            val recyclerView = weather_recycler_view
            recyclerView.layoutManager= LinearLayoutManager(this)
            recyclerView.adapter =
                CitiesWeatherListAdapter(
                    citiesWeatherList,
                    R.layout.list_item_weather,
                    { currentWeather: CurrentWeather -> weatherItemClicked(currentWeather) })
        }
    }
}
