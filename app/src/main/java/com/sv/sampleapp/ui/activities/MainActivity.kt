package com.sv.sampleapp.ui.activities

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import com.sv.sampleapp.CountryApp
import com.sv.sampleapp.R
import com.sv.sampleapp.adapter.CountryListAdapter
import com.sv.sampleapp.adapter.CountrySelectionListener
import com.sv.sampleapp.component.AppComponent
import com.sv.sampleapp.model.CountryListResponse
import com.sv.sampleapp.model.datastore.CountryDataStore
import com.sv.sampleapp.ui.fragments.CountryInfoFragment
import com.sv.sampleapp.ui.fragments.WeatherDashboardFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CountrySelectionListener {

    companion object {
        const val EXTRA_SELECT_COUNTRY_POSITION = "EXTRA_SELECT_COUNTRY_POSITION"
    }

    private lateinit var appComponent: AppComponent
    private lateinit var countryDataStore: CountryDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()

        appComponent = (application as CountryApp).getAppComponent()
        countryDataStore = appComponent.getCountryDataStore()


        if (countryDataStore.countyList == null) {
            fetchCountyList()
        } else {
            loadFromCache()
        }
    }


    private fun initUI() {
        setupToolbar()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Country Weather"
        supportActionBar?.show()
        var actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        drawerLayout.openDrawer(Gravity.START)
        actionBarDrawerToggle.syncState()
    }

    fun loadFromCache() {
        loadNavigation()

        loadCountryFragment(countryDataStore.lastSelectedCountyPosition)
        loadWeatherFragment(countryDataStore.lastSelectedCountyPosition)
    }

    private fun fetchCountyList() {
        val call = appComponent.getCountryService().getCountryList()
        call.enqueue(object : Callback<CountryListResponse> {
            override fun onFailure(call: Call<CountryListResponse>, t: Throwable) {
                // show error with snackbar
                Log.e("MainActivity", "CountryList failure", t)
            }

            override fun onResponse(call: Call<CountryListResponse>, response: Response<CountryListResponse>) {
                val countryListResponse = response.body()
                if (response.isSuccessful && countryListResponse != null) {
                    countryDataStore.countyList = countryListResponse
                    countryDataStore.lastSelectedCountyPosition = 0
                    loadFromCache()
                }
            }
        })
    }

    private fun loadNavigation() {
        recyclerView.adapter = CountryListAdapter(countryDataStore.countyList, this)
    }

    override fun onCountrySelected(position: Int) {
        countryDataStore.lastSelectedCountyPosition = position
        //TODO: Refactor this to send data directly to loaded fragments instead of replacing again.
        loadCountryFragment(position)
        loadWeatherFragment(position)
        drawerLayout.closeDrawer(Gravity.START)
    }

    private fun loadCountryFragment(position: Int) {
        val fragment = CountryInfoFragment()
        val bundle = Bundle()
        bundle.putInt(EXTRA_SELECT_COUNTRY_POSITION, position)
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.countryInfoContainer, fragment)
        transaction.commitAllowingStateLoss()
    }

    private fun loadWeatherFragment(position: Int) {
        val item = countryDataStore.getCountryItem(position)
        val lat = item?.latlng?.get(0)
        val lon = item?.latlng?.get(1)

        if (lat != null && lon != null) {
            val fragment = WeatherDashboardFragment()
            val bundle = Bundle()
            bundle.putDouble(WeatherDashboardFragment.EXTRA_LATITUDE, lat)
            bundle.putDouble(WeatherDashboardFragment.EXTRA_LONGITUDE, lon)
            fragment.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.weatherContainer, fragment)
            transaction.commitAllowingStateLoss()
        }
    }

    override fun onStop() {
        super.onStop()
        countryDataStore.lastVisibleTimeInMillis = System.currentTimeMillis()
        //close the activity to launch it freshly on reopen
        finish()
    }
}
