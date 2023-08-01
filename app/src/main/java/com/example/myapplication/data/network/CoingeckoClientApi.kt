package com.example.myapplication.data.network

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import com.example.myapplication.data.state.ResultNetwork
import retrofit2.Retrofit
import javax.inject.Inject

interface CoingeckoClientApi {
    suspend fun loadCryptocurrencyList(size: String): ResultNetwork
}

class CoingeckoClientApiImpl @Inject constructor(
    private val retrofit: Retrofit,
    private val service: CoingeckoApi = retrofit.create(CoingeckoApi::class.java)
) : CoingeckoClientApi{
    override suspend fun loadCryptocurrencyList(size: String): ResultNetwork {
        return try {
            if (size.toInt() <= 0) return ResultNetwork.Error

            val response = service.marketCryptocurrency(size = size)
            return ResultNetwork.Success(response)
        }catch (e: Throwable) {
            ResultNetwork.Error
        }
    }
}