package com.example.myapplication.domain.repository

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import com.example.myapplication.domain.data.CryptocurrencyModel

interface ICryptocurrencyRepository {
    suspend fun cryptocurrencyList(itemsCount: Int): List<CryptocurrencyModel>
}