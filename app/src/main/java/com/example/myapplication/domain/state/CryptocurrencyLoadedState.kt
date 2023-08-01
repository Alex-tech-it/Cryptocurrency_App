package com.example.myapplication.domain.state

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import com.example.myapplication.domain.data.CryptocurrencyModel

sealed class CryptocurrencyLoadedState {
    class SuccessLoad(val data: List<CryptocurrencyModel>): CryptocurrencyLoadedState()
    object ErrorLoad: CryptocurrencyLoadedState()
}