package com.sv.sampleapp.network.api


import com.sv.sampleapp.network.RetrofitModule
import com.sv.sampleapp.network.WeatherRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [RetrofitModule::class])
class WeatherModule {

    @Provides
    fun provideWeatherService(@WeatherRetrofit retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

}