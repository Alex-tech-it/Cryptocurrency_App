package com.example.myapplication.presentation.presentation.widgets

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
fun ChangedPriceDetail(
    valueChanged: String,
    valuePercentageChanged: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.cryptocurrency_price_changed),
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
            text = valueChanged,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Start,
            )
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.cryptocurrency_price_changed_percentage),
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
            text = valuePercentageChanged,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.darkOrange),
                textAlign = TextAlign.Start,
            )
        )
    }
}