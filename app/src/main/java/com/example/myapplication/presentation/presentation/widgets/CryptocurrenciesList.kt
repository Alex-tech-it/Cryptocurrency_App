package com.example.myapplication.presentation.presentation.widgets

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.myapplication.R
import com.example.myapplication.domain.data.CryptocurrencyModel
import com.example.myapplication.presentation.viewModel.CryptocurrenciesViewModel

@Composable
fun CryptocurrenciesList(
    viewModel: CryptocurrenciesViewModel,
    onItemClick: (item: CryptocurrencyModel) -> Unit
) {
    val cryptocurrenciesList = viewModel.cryptocurrenciesFlow.collectAsLazyPagingItems()

    LazyColumn {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 20.dp, bottom = 12.dp),
                text = stringResource(id = R.string.cryptocurrency_header),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            )

        }

        items(cryptocurrenciesList.itemCount) { indexItem ->
            cryptocurrenciesList[indexItem]?.let {
                CryptocurrencyCard(cryptocurrency = it, onItemClick = onItemClick)
            }
        }

        when(cryptocurrenciesList.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item {
                    CryptocurrenciesLoadingItems()
                }
            }
            is LoadState.Error -> viewModel.showErrorLimitMessage()
        }

        when(cryptocurrenciesList.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item {
                    CryptocurrenciesLoadingProgressBar()
                }
            }
            is LoadState.Error -> viewModel.showErrorLimitMessage()

        }

    }
}

