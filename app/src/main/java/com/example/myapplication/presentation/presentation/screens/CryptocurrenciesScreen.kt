package com.example.myapplication.presentation.presentation.screens

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.presentation.presentation.navigation.Screen
import com.example.myapplication.presentation.presentation.widgets.CryptocurrenciesList
import com.example.myapplication.presentation.viewModel.CryptocurrenciesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptocurrenciesScreen(
    navController: NavHostController
) {
    val viewModel: CryptocurrenciesViewModel = hiltViewModel()
    val snackBarHostState = remember { SnackbarHostState() }
    val errorSnackBarTitle = stringResource(id = R.string.request_limit_error)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        content = { innerPadding ->
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),) {
                CryptocurrenciesList(viewModel = viewModel, onItemClick = {
                    navController
                        .currentBackStackEntry?.savedStateHandle?.set("cryptocurrency", it)
                    navController.navigate(Screen.CryptoDetail.route)
                })
            }
        }
    )

    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collect {
            snackBarHostState.showSnackbar(errorSnackBarTitle)
        }
    }

}