package com.example.myapplication.data.network

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import com.example.myapplication.data.model.NetworkCryptocurrencyModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CoingeckoApi {

    @GET("markets")
    suspend fun marketCryptocurrency(
        @Query("vs_currency") currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") size: String,
    ): List<NetworkCryptocurrencyModel>
}