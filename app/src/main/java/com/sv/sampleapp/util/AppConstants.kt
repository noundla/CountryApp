package com.sv.sampleapp.util

object AppConstants {
    const val COUNTRY_LIST_BASE_URL = "https://restcountries.eu"
    const val WEATHER_BASE_URL = "http://api.openweathermap.org"


    fun getFlagUrl(countryCode: String) = "http://www.geognos.com/api/en/countries/flag/$countryCode.png"
}