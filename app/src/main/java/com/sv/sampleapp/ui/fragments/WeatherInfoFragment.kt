package com.sv.sampleapp.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sv.sampleapp.R
import com.sv.sampleapp.model.ListItem
import com.sv.sampleapp.util.Utils
import kotlinx.android.synthetic.main.fragment_weather_info.*

class WeatherInfoFragment : Fragment() {
    companion object {
        const val EXTRA_WEATHER_INFO = "EXTRA_WEATHER_INFO"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null && arguments!!.containsKey(EXTRA_WEATHER_INFO)) {
            updateWeatherInfo(arguments!!.getParcelable<ListItem>(EXTRA_WEATHER_INFO))
        }
    }

    private fun updateWeatherInfo(item: ListItem?) {
        if (item != null) {
            dateValue.text = Utils.getDateString(item.dt, "dd-MM-yyyy hh:mm a")
            tempValue.text = "${item.temp.min} - ${item.temp.max}"
            pressureValue.text = "${item.pressure}"
            humidityValue.text = "${item.humidity}"
        }
    }

}
