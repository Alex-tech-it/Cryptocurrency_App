package com.example.myapplication.presentation.presentation.widgets

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.data.CryptocurrencyModel


@Composable
fun CryptocurrencyCard(
    cryptocurrency: CryptocurrencyModel,
    onItemClick: (item: CryptocurrencyModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.inversePrimary
        )
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(cryptocurrency) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    AsyncImage(
                        model = cryptocurrency.imageUrl,
                        contentDescription = cryptocurrency.symbol,
                        modifier = Modifier.size(45.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = cryptocurrency.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Text(
                        text = cryptocurrency.symbol,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                        ),
                        modifier = Modifier.align(Alignment.Top)
                    )
                }
                Row(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.size(12.dp))
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ){
                        CryptocurrencyBoundsPrice(
                            modifier = Modifier.align(Alignment.End),
                            title = stringResource(id = R.string.cryptocurrency_max_day),
                            value = cryptocurrency.highPriceDay,
                            colorTitle = colorResource(id = R.color.green)
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        CryptocurrencyBoundsPrice(
                            modifier = Modifier.align(Alignment.End),
                            title = stringResource(id = R.string.cryptocurrency_min_day),
                            value = cryptocurrency.lowPriceDay,
                            colorTitle = colorResource(id = R.color.red)
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = cryptocurrency.getPriceWithFiat(stringResource(id = R.string.cryptocurrency_usd)) ,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Composable
fun CryptocurrencyBoundsPrice(
    modifier: Modifier,
    title: String,
    value: String,
    colorTitle: Color
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 14.sp,
                color = colorResource(id = R.color.greyHint)
            )
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = value,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                color = colorTitle
            )
        )
    }
}