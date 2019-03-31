package com.sv.sampleapp.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Coord(@SerializedName("lon")
                 val lon: Double = 0.0,
                 @SerializedName("lat")
                 val lat: Double = 0.0)


data class Temp(@SerializedName("min")
                val min: Double = 0.0,
                @SerializedName("max")
                val max: Double = 0.0,
                @SerializedName("eve")
                val eve: Double = 0.0,
                @SerializedName("night")
                val night: Double = 0.0,
                @SerializedName("day")
                val day: Double = 0.0,
                @SerializedName("morn")
                val morn: Double = 0.0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(min)
        parcel.writeDouble(max)
        parcel.writeDouble(eve)
        parcel.writeDouble(night)
        parcel.writeDouble(day)
        parcel.writeDouble(morn)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Temp> {
        override fun createFromParcel(parcel: Parcel): Temp {
            return Temp(parcel)
        }

        override fun newArray(size: Int): Array<Temp?> {
            return arrayOfNulls(size)
        }
    }
}


data class WeatherItem(@SerializedName("icon")
                       val icon: String = "",
                       @SerializedName("description")
                       val description: String = "",
                       @SerializedName("main")
                       val main: String = "",
                       @SerializedName("id")
                       val id: Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(icon)
        parcel.writeString(description)
        parcel.writeString(main)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherItem> {
        override fun createFromParcel(parcel: Parcel): WeatherItem {
            return WeatherItem(parcel)
        }

        override fun newArray(size: Int): Array<WeatherItem?> {
            return arrayOfNulls(size)
        }
    }
}


data class WeatherResponse(@SerializedName("city")
                           val city: City,
                           @SerializedName("cnt")
                           val cnt: Int = 0,
                           @SerializedName("cod")
                           val cod: String = "",
                           @SerializedName("message")
                           val message: Double = 0.0,
                           @SerializedName("list")
                           val list: List<ListItem>?)


data class City(@SerializedName("country")
                val country: String = "",
                @SerializedName("coord")
                val coord: Coord,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("population")
                val population: Int = 0)


data class ListItem(@SerializedName("dt")
                    val dt: Long = 0,
                    @SerializedName("temp")
                    val temp: Temp,
                    @SerializedName("deg")
                    val deg: Int = 0,
                    @SerializedName("weather")
                    val weather: List<WeatherItem>?,
                    @SerializedName("humidity")
                    val humidity: Int = 0,
                    @SerializedName("pressure")
                    val pressure: Double = 0.0,
                    @SerializedName("clouds")
                    val clouds: Int = 0,
                    @SerializedName("speed")
                    val speed: Double = 0.0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readParcelable(Temp::class.java.classLoader),
        parcel.readInt(),
        parcel.createTypedArrayList(WeatherItem),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(dt)
        parcel.writeParcelable(temp, flags)
        parcel.writeInt(deg)
        parcel.writeTypedList(weather)
        parcel.writeInt(humidity)
        parcel.writeDouble(pressure)
        parcel.writeInt(clouds)
        parcel.writeDouble(speed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListItem> {
        override fun createFromParcel(parcel: Parcel): ListItem {
            return ListItem(parcel)
        }

        override fun newArray(size: Int): Array<ListItem?> {
            return arrayOfNulls(size)
        }
    }
}


