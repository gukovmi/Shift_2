package com.example.shift_2.modules.cities_weather_list

import CurrentWeather
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shift_2.R
import com.example.shift_2.modules.city_weather_details.CityWeatherActivity
import com.example.shift_2.presentation.base.BaseView
import kotlinx.android.synthetic.main.activity_cities_weather_list.*

class CitiesWeatherListActivity : AppCompatActivity(), BaseView, CitiesWeatherListView {

    private val presenter=CitiesWeatherListPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_weather_list)

        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun weatherItemClicked(currentWeather: CurrentWeather) {
        val intent= Intent(this, CityWeatherActivity::class.java)
        intent.putExtra("currentWeather", currentWeather)
        startActivity(intent)
    }

    override fun showCitiesWeatherList(citiesWeatherList: MutableList<CurrentWeather>) {
        citiesWeatherRecyclerView.layoutManager= LinearLayoutManager(this@CitiesWeatherListActivity)
        citiesWeatherRecyclerView.adapter =
            CitiesWeatherListAdapter(
                citiesWeatherList,
                R.layout.list_item_weather,
                { currentWeather: CurrentWeather -> weatherItemClicked(currentWeather) })
    }
}
