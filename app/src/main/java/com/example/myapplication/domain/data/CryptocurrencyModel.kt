package com.example.myapplication.domain.data

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import android.os.Parcel
import android.os.Parcelable

data class CryptocurrencyModel(
    val id: String = "",
    val symbol: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val currentPrice: String = "",
    val marketCap: String = "",
    val highPriceDay: String = "",
    val lowPriceDay: String = "",
    val priceChangeDay: String = "",
    val priceChangePercentageDay: String = "",
    val lastUpdated: String = ""
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    fun getPriceWithFiat(titleFiat: String) = "$currentPrice $titleFiat"
    fun getMarketCapFiat(titleFiat: String) = "$marketCap $titleFiat"
    fun getPriceChangeFiat(titleFiat: String) = "$priceChangeDay $titleFiat"
    fun getPriceChangePercentageCapFiat(titleFiat: String) = "$priceChangePercentageDay $titleFiat"

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeString(currentPrice)
        parcel.writeString(marketCap)
        parcel.writeString(highPriceDay)
        parcel.writeString(lowPriceDay)
        parcel.writeString(priceChangeDay)
        parcel.writeString(priceChangePercentageDay)
        parcel.writeString(lastUpdated)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CryptocurrencyModel> {
        override fun createFromParcel(parcel: Parcel): CryptocurrencyModel {
            return CryptocurrencyModel(parcel)
        }

        override fun newArray(size: Int): Array<CryptocurrencyModel?> {
            return arrayOfNulls(size)
        }
    }
}