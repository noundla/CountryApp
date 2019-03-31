package com.sv.sampleapp.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.squareup.picasso.Picasso
import com.sv.sampleapp.R
import com.sv.sampleapp.model.CountryDataStore
import com.sv.sampleapp.util.AppConstants
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    companion object {
        const val MINUTE = 60 * 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // select next county if time is passed by 1 minute
        if (CountryDataStore.countyList != null && (System.currentTimeMillis() - CountryDataStore.lastVisibleTimeInMillis) > MINUTE) {
            CountryDataStore.lastSelectedCountyPosition = CountryDataStore.lastSelectedCountyPosition + 1
        }

        loadSelectedCountryFlag()

        Handler().postDelayed({
            launchHomeScreen()
        }, 5000)
    }

    private fun loadSelectedCountryFlag() {
        val item = CountryDataStore.getCountryItem(CountryDataStore.lastSelectedCountyPosition)
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
