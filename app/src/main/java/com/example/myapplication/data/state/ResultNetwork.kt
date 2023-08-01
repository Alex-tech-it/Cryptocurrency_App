package com.example.myapplication.data.state

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import com.example.myapplication.data.model.NetworkCryptocurrencyModel

sealed class ResultNetwork {
    class Success(val dataList: List<NetworkCryptocurrencyModel>): ResultNetwork()
    object Error: ResultNetwork()
}

