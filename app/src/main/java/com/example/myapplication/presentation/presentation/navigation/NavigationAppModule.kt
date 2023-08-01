package com.example.myapplication.presentation.presentation.navigation

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.domain.data.CryptocurrencyModel
import com.example.myapplication.presentation.presentation.screens.CryptocurrenciesScreen
import com.example.myapplication.presentation.presentation.screens.CryptocurrencyDetailScreen

@Composable
fun NavigationModule(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.CryptoHome.route) {
        composable(route = Screen.CryptoHome.route) {
            CryptocurrenciesScreen(navController = navController)
        }
        composable(
            route = Screen.CryptoDetail.route,
        ) {
            val model =
                navController.previousBackStackEntry?.
                savedStateHandle?.get<CryptocurrencyModel>("cryptocurrency")
            CryptocurrencyDetailScreen(
                cryptocurrencyModel = model,
                onBack = { navController.popBackStack() }
            )
        }
    }
}