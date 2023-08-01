package com.example.myapplication.presentation.presentation

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.presentation.navigation.NavigationModule
import com.example.myapplication.presentation.presentation.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptocurrenciesActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavigationModule(navController = navController)
            }
        }
    }

}
