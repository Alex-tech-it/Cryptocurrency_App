package com.example.myapplication.domain.useCase

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import com.example.myapplication.domain.repository.ICryptocurrencyRepository
import com.example.myapplication.domain.state.CryptocurrencyLoadedState
import javax.inject.Inject

open class CryptocurrencyItemsUseCase @Inject constructor(
    private val cryptocurrencyRepository: ICryptocurrencyRepository
) {
    suspend fun execute(countItems: Int): CryptocurrencyLoadedState {
        val cryptocurrencyList =
            cryptocurrencyRepository.cryptocurrencyList(countItems)
        if (cryptocurrencyList.isEmpty()) return CryptocurrencyLoadedState.ErrorLoad
        return CryptocurrencyLoadedState.SuccessLoad(cryptocurrencyList)
    }
}