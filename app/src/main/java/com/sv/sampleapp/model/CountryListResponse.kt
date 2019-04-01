package com.sv.sampleapp.model

import com.google.gson.annotations.SerializedName

typealias CountryListResponse = ArrayList<CountryItem>

data class CountryItem(
    @SerializedName("area")
    val area: Double? = 0.0,
    @SerializedName("nativeName")
    val nativeName: String? = "",
    @SerializedName("capital")
    val capital: String? = "",
    @SerializedName("demonym")
    val demonym: String? = "",
    @SerializedName("alpha2Code")
    val alphaCode2: String? = "",
    @SerializedName("languages")
    val languages: List<String>?,
    @SerializedName("borders")
    val borders: List<String>?,
    @SerializedName("subregion")
    val subregion: String? = "",
    @SerializedName("callingCodes")
    val callingCodes: List<String>?,
    @SerializedName("gini")
    val gini: Double? = 0.0,
    @SerializedName("relevance")
    val relevance: String? = "",
    @SerializedName("population")
    val population: Int? = 0,
    @SerializedName("numericCode")
    val numericCode: String? = "",
    @SerializedName("alpha3Code")
    val alphaCode3: String? = "",
    @SerializedName("topLevelDomain")
    val topLevelDomain: List<String>?,
    @SerializedName("timezones")
    val timezones: List<String>?,
    @SerializedName("translations")
    val translations: Translations?,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("altSpellings")
    val altSpellings: List<String>?,
    @SerializedName("region")
    val region: String? = "",
    @SerializedName("latlng")
    val latlng: List<Double>?,
    @SerializedName("currencies")
    val currencies: List<String>?
)


data class Translations(
    @SerializedName("de")
    val de: String = "",
    @SerializedName("ja")
    val ja: String = "",
    @SerializedName("it")
    val it: String = "",
    @SerializedName("fr")
    val fr: String = "",
    @SerializedName("es")
    val es: String = ""
)