package com.example.myapplication.domain.model

import android.os.Parcel
import androidx.test.filters.SmallTest
import com.example.myapplication.domain.data.CryptocurrencyModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


@SmallTest
internal class CryptocurrencyModelTest {

    private lateinit var cryptocurrencyModel: CryptocurrencyModel
    private val currentPrice = "1000"
    private val marketCap = "1000"
    private val priceChangeDay = "100"
    private val priceChangePercentageDay = "10"
    private val placeHolder = "USDT"

    @BeforeEach
    fun setup() {
        cryptocurrencyModel = CryptocurrencyModel(
            id = "1",
            currentPrice = currentPrice,
            marketCap = marketCap,
            priceChangeDay = priceChangeDay,
            priceChangePercentageDay = priceChangePercentageDay,
        )
    }

    @Test
    fun test_model_parcelable() {
        val parcel = Parcel.obtain()
        cryptocurrencyModel.writeToParcel(parcel, 0)

        parcel.setDataPosition(0)
        val createdFromParcel = CryptocurrencyModel.CREATOR.createFromParcel(parcel)
        Assertions.assertEquals(cryptocurrencyModel, createdFromParcel)
    }

    @Test
    fun test_get_price_with_fiat() {
        Assertions.assertEquals(
            cryptocurrencyModel.getPriceWithFiat(placeHolder),
            "$currentPrice $placeHolder"
        )
    }

    @Test
    fun test_get_market_up_with_fiat() {
        Assertions.assertEquals(
            cryptocurrencyModel.getMarketCapFiat(placeHolder),
            "$marketCap $placeHolder"
        )
    }

    @Test
    fun test_get_price_change_with_fiat() {
        Assertions.assertEquals(
            cryptocurrencyModel.getPriceChangeFiat(placeHolder),
            "$priceChangeDay $placeHolder"
        )
    }

    @Test
    fun test_get_price_change_perc_with_fiat() {
        Assertions.assertEquals(
            cryptocurrencyModel.getPriceChangePercentageCapFiat(placeHolder),
            "$priceChangePercentageDay $placeHolder"
        )
    }
}