package com.example.myapplication.data.model

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import com.example.myapplication.domain.data.CryptocurrencyModel
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCryptocurrencyModel(
    val ath: String = "",
    val ath_change_percentage: String = "",
    val ath_date: String = "",
    val atl: String = "",
    val atl_change_percentage: String = "",
    val atl_date: String = "",
    val circulating_supply: String = "",
    val current_price: String = "",
    val fully_diluted_valuation: String = "",
    val high_24h: String = "",
    val id: String = "",
    val image: String = "",
    val last_updated: String = "",
    val low_24h: String = "",
    val market_cap: String = "",
    val market_cap_change_24h: String = "",
    val market_cap_change_percentage_24h: String = "",
    val market_cap_rank: String = "",
    val max_supply: String = "",
    val name: String = "",
    val price_change_24h: String = "",
    val price_change_percentage_24h: String = "",
    val roi: Any = Any(),
    val symbol: String = "",
    val total_supply: String = "",
    val total_volume: String = ""
)

fun NetworkCryptocurrencyModel.toCryptocurrencyModel(): CryptocurrencyModel {
    return CryptocurrencyModel(
        id = this.id,
        symbol = this.symbol,
        name = this.name,
        imageUrl = this.image,
        currentPrice = this.current_price,
        marketCap = this.market_cap,
        highPriceDay = this.high_24h,
        lowPriceDay = this.low_24h,
        priceChangeDay = this.price_change_24h,
        priceChangePercentageDay = this.price_change_percentage_24h,
        lastUpdated = this.last_updated
    )
}
