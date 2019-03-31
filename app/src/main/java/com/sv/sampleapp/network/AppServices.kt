package com.sv.sampleapp.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sv.sampleapp.BuildConfig
import com.sv.sampleapp.util.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppServices {

    val normalClient = OkHttpClient().newBuilder()
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()

    val countryApi = Retrofit
        .Builder()
        .baseUrl(AppConstants.COUNTRY_LIST_BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().serializeNulls().create())
        ) // allow null as values for keys
        .client(normalClient)
        .build()

    val weatherApi = Retrofit
        .Builder()
        .baseUrl(AppConstants.WEATHER_BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().serializeNulls().create())
        ) // allow null as values for keys
        .client(normalClient)
        .build()


    fun getWeatherService(): WeatherService {
        return weatherApi.create(WeatherService::class.java)
    }

    fun getCountyService(): CountryService {
        return countryApi.create(CountryService::class.java)
    }

}