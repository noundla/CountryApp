package com.sv.sampleapp.model

object CountryDataStore {
    @JvmStatic
    var countyList: ArrayList<CountryItem>? = null

    @JvmStatic
    var lastSelectedCountyPosition = 0

    @JvmStatic
    var lastVisibleTimeInMillis: Long = 0

    fun getCountryItem(position: Int): CountryItem? {
        return if (countyList != null && countyList!!.size > position) countyList!![position] else null
    }

}