package com.sv.sampleapp.component

import com.squareup.picasso.Picasso
import com.sv.sampleapp.model.datastore.CountryDataStore
import com.sv.sampleapp.model.datastore.CountryDataStoreModule
import com.sv.sampleapp.network.PicassoModule
import com.sv.sampleapp.network.api.CountryModule
import com.sv.sampleapp.network.api.CountryService
import com.sv.sampleapp.network.api.WeatherModule
import com.sv.sampleapp.network.api.WeatherService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CountryModule::class, WeatherModule::class, PicassoModule::class, CountryDataStoreModule::class])
interface AppComponent {

    fun getCountryService(): CountryService

    fun getWeatherService(): WeatherService

    fun getPicasso(): Picasso
    @Singleton
    fun getCountryDataStore(): CountryDataStore

}