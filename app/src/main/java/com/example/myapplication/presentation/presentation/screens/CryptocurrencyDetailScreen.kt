package com.example.myapplication.presentation.presentation.screens

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.domain.data.CryptocurrencyModel
import com.example.myapplication.presentation.presentation.widgets.BoundsPriceDetail
import com.example.myapplication.presentation.presentation.widgets.ChangedPriceDetail
import com.example.myapplication.presentation.presentation.widgets.CollapsingToolBarDetailScreen
import com.example.myapplication.presentation.presentation.widgets.DetailEmptyModelScreen
import com.example.myapplication.presentation.presentation.widgets.MarketCapDetail
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@Composable
fun CryptocurrencyDetailScreen(
    cryptocurrencyModel: CryptocurrencyModel?,
    onBack: () -> Unit
) {
    if (cryptocurrencyModel != null)
        CryptocurrencyDetailScreenContent(
            cryptocurrencyModel = cryptocurrencyModel,
            onBack = onBack
        )
    else
        DetailEmptyModelScreen()
}

@Composable
fun CryptocurrencyDetailScreenContent(
    cryptocurrencyModel: CryptocurrencyModel,
    onBack: () -> Unit
) {
    val collapsingState = rememberCollapsingToolbarScaffoldState()
    val fiatStr = stringResource(id = R.string.cryptocurrency_usd)

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = collapsingState,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            CollapsingToolBarDetailScreen(
                model = cryptocurrencyModel,
                collapsingState = collapsingState,
                collapsingToolbarScope = this
            ) { onBack() }
        }
        ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = cryptocurrencyModel.getPriceWithFiat(fiatStr),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.white)
                    )
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)) {
                    BoundsPriceDetail(
                        title = stringResource(id = R.string.cryptocurrency_max_day_description),
                        value = cryptocurrencyModel.highPriceDay,
                        colorTitle = colorResource(id = R.color.green),
                        modifier = Modifier.weight(1F)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    BoundsPriceDetail(
                        title = stringResource(id = R.string.cryptocurrency_min_day_description),
                        value = cryptocurrencyModel.lowPriceDay,
                        colorTitle = colorResource(id = R.color.red),
                        modifier = Modifier.weight(1F)
                    )
                }
                MarketCapDetail(
                    value = cryptocurrencyModel.getMarketCapFiat(fiatStr)
                )
                ChangedPriceDetail(
                    valueChanged = cryptocurrencyModel.getPriceChangeFiat(fiatStr),
                    valuePercentageChanged = cryptocurrencyModel.getPriceChangePercentageCapFiat(
                        stringResource(id = R.string.percentage)
                    )
                )
                Spacer(modifier = Modifier.size(width = 1.dp, height = 200.dp))
            }
        }
    }
}



