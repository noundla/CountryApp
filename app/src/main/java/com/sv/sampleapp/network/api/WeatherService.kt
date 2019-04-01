package com.sv.sampleapp.network.api

import com.sv.sampleapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/forecast/daily?appid=1867722b6af87e1d0388e10c5a94be34")
    fun getWeather(@Query("lat") latitude: Double, @Query("lon") longitude: Double): Call<WeatherResponse>
}