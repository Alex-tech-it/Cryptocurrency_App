package com.example.myapplication.presentation.presentation.widgets

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun MarketCapDetail(
    value: String,
) {
    Box(modifier = Modifier
        .padding(vertical = 12.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.cryptocurrency_market_cap),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.greyHint),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = value,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Start,
                )
            )
        }
    }
}