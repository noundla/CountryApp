package com.sv.sampleapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.sv.sampleapp.CountryApp
import com.sv.sampleapp.R
import com.sv.sampleapp.model.datastore.CountryDataStore
import com.sv.sampleapp.util.AppConstants
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    companion object {
        const val MINUTE = 60 * 1000
    }


    private lateinit var countryDataStore: CountryDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        countryDataStore = (application as CountryApp).getAppComponent().getCountryDataStore()
        // select next county if time is passed by 1 minute
        if (countryDataStore.countyList != null && (System.currentTimeMillis() - countryDataStore.lastVisibleTimeInMillis) > MINUTE) {
            countryDataStore.lastSelectedCountyPosition = countryDataStore.lastSelectedCountyPosition + 1
        }

        loadSelectedCountryFlag()

        Handler().postDelayed({
            launchHomeScreen()
        }, 5000)
    }

    private fun loadSelectedCountryFlag() {
        val item = countryDataStore.getCountryItem(countryDataStore.lastSelectedCountyPosition)
        if (item != null) {
            val flagUrl = AppConstants.getFlagUrl(item.alphaCode2 ?: "")
            Picasso.get()
                .load(flagUrl)
                .error(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }

    private fun launchHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
