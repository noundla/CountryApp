package com.sv.sampleapp.network.api

import com.sv.sampleapp.network.CountryRetrofit
import com.sv.sampleapp.network.RetrofitModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class CountryModule {

    @Provides
    fun provideCountyService(@CountryRetrofit retrofit: Retrofit): CountryService {
        return retrofit.create(CountryService::class.java)
    }

}