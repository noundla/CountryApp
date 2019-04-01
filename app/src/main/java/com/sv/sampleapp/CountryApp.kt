package com.sv.sampleapp

import android.app.Application
import com.sv.sampleapp.component.AppComponent
import com.sv.sampleapp.component.DaggerAppComponent
import com.sv.sampleapp.network.BuildModule
import com.sv.sampleapp.network.ContextModule

class CountryApp : Application() {
    companion object {
        private var appComponent: AppComponent? = null
    }

    fun getAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                .builder()
                .buildModule(BuildModule(BuildConfig.DEBUG))
                .contextModule(ContextModule(applicationContext))
                .build()
        }
        return appComponent!!
    }

}