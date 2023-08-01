package com.example.myapplication.data.repository

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import com.example.myapplication.data.model.NetworkCryptocurrencyModel
import com.example.myapplication.data.model.toCryptocurrencyModel
import com.example.myapplication.data.network.CoingeckoClientApi
import com.example.myapplication.data.state.ResultNetwork
import com.example.myapplication.domain.data.CryptocurrencyModel
import com.example.myapplication.domain.repository.ICryptocurrencyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptocurrencyRepositoryImpl @Inject constructor(
    private val serviceApi: CoingeckoClientApi,
    private val dispatcher: CoroutineDispatcher
) : ICryptocurrencyRepository {

    var currentLoadedItems: Int = 0
        private set

    override suspend fun cryptocurrencyList(
        itemsCount: Int
    ): List<CryptocurrencyModel> {
        return withContext(dispatcher) {
            try {
                when(val response = serviceApi.loadCryptocurrencyList(
                    (currentLoadedItems + itemsCount).toString())
                ) {
                    is ResultNetwork.Success -> {
                        val responseList = getSubList(response.dataList, itemsCount)
                        currentLoadedItems += responseList.size
                        responseList.map { it.toCryptocurrencyModel() }
                    }
                    is ResultNetwork.Error -> listOf()
                }
            } catch (e: Throwable) {
                return@withContext listOf()
            }

        }
    }

    private fun getSubList(
        dataList: List<NetworkCryptocurrencyModel>,
        countItems: Int
    ): List<NetworkCryptocurrencyModel> {
        val sizeResponse = dataList.size
        return dataList.subList(
            if (sizeResponse < countItems) 0 else sizeResponse - countItems,
            sizeResponse
        )
    }

}