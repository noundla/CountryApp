package com.sv.sampleapp.model.datastore

import com.sv.sampleapp.model.CountryItem


class CountryDataStore {

    var countyList: ArrayList<CountryItem>? = null

    var lastSelectedCountyPosition = 0

    var lastVisibleTimeInMillis: Long = 0

    fun getCountryItem(position: Int): CountryItem? {
        return if (countyList != null && countyList!!.size > position) countyList!![position] else null
    }

}