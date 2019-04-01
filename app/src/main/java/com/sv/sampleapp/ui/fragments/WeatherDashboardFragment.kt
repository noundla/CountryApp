package com.sv.sampleapp.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sv.sampleapp.CountryApp

import com.sv.sampleapp.R
import com.sv.sampleapp.component.AppComponent
import com.sv.sampleapp.model.ListItem
import com.sv.sampleapp.model.WeatherResponse
import com.sv.sampleapp.network.api.WeatherService
import kotlinx.android.synthetic.main.fragment_weather_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherDashboardFragment : Fragment() {
    companion object {
        const val EXTRA_LATITUDE = "EXTRA_LATITUDE"
        const val EXTRA_LONGITUDE = "EXTRA_LONGITUDE"
    }

    lateinit var weatherService: WeatherService

    var latitude: Double? = null
    var longitude: Double? = null

    private lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = (activity?.application as CountryApp).getAppComponent()
        weatherService = appComponent.getWeatherService()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_dashboard, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadArguments()
        loadWeatherInfo()
    }

    private fun loadArguments() {
        if (arguments != null) {
            if (arguments!!.containsKey(EXTRA_LATITUDE)) {
                latitude = arguments!!.getDouble(EXTRA_LATITUDE)
            }
            if (arguments!!.containsKey(EXTRA_LONGITUDE)) {
                longitude = arguments!!.getDouble(EXTRA_LONGITUDE)
            }
        }
    }

    private fun loadWeatherInfo() {
        if (latitude != null && longitude != null) {
            val call = weatherService.getWeather(latitude!!, longitude!!)
            call.enqueue(object : Callback<WeatherResponse> {
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    //Parse response for today/tomorrow data and set to pager adapter
                    if (response.isSuccessful && response.body() != null) {
                        val weatherResponse = response.body()!!;
                        if (weatherResponse.list != null && weatherResponse.list.isNotEmpty()) {
                            viewPager.adapter = WeatherInfoAdapter(childFragmentManager, weatherResponse.list!!)
                            tabLayout.setupWithViewPager(viewPager)
                        }
                    }
                }
            })
        }
    }


    inner class WeatherInfoAdapter : FragmentStatePagerAdapter {
        private val weatherList: List<ListItem>

        constructor(fm: FragmentManager?, weatherList: List<ListItem>) : super(fm) {
            this.weatherList = weatherList
        }

        override fun getItem(pos: Int): Fragment {
            val fragment = WeatherInfoFragment()
            val bundle = Bundle()
            bundle.putParcelable(WeatherInfoFragment.EXTRA_WEATHER_INFO, weatherList.get(pos))
            fragment.arguments = bundle
            return fragment
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> getString(R.string.today)
                1 -> getString(R.string.tomorrow)
                else -> ""
            }
        }

        override fun getCount(): Int {
            return if (weatherList.size > 1) 2 else 1
        }

    }

}
