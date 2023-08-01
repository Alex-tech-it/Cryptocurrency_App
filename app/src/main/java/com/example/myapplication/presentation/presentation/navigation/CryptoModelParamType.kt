package com.example.myapplication.presentation.presentation.navigation

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import android.os.Bundle
import androidx.navigation.NavType
import com.example.myapplication.domain.data.CryptocurrencyModel
import com.google.gson.Gson

class CryptoModelParamType : NavType<CryptocurrencyModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): CryptocurrencyModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CryptocurrencyModel {
        return Gson().fromJson(value, CryptocurrencyModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CryptocurrencyModel) {
        bundle.putParcelable(key, value)
    }
}