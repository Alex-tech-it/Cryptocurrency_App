package com.example.myapplication.presentation.presentation.navigation

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

sealed class Screen(val route: String) {
    object CryptoHome : Screen("CryptoHome")
    object CryptoDetail : Screen("CryptoDetail")
}