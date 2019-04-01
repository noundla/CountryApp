package com.sv.sampleapp.network.api

import com.sv.sampleapp.model.CountryListResponse
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {


    @GET("/rest/v1/all")
    fun getCountryList(): Call<CountryListResponse>
}