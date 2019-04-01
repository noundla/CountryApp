package com.sv.sampleapp.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sv.sampleapp.util.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpClientModule::class])
class RetrofitModule {

    @Provides
    @CountryRetrofit
    fun provideCountryRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(AppConstants.COUNTRY_LIST_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @WeatherRetrofit
    fun provideWeatherRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(AppConstants.WEATHER_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().create()
    }
}