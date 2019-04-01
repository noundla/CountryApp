package com.sv.sampleapp.model.datastore

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CountryDataStoreModule {


    @Provides
    @Singleton
    fun getDataStore(): CountryDataStore {
        return CountryDataStore()
    }
}