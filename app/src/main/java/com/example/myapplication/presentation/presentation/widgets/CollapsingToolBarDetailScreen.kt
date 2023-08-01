package com.example.myapplication.presentation.presentation.widgets

/**
 * @author Alex-tech-it
 * Github - https://github.com/Alex-tech-it
 */

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.domain.data.CryptocurrencyModel
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.CollapsingToolbarScope

@Composable
fun CollapsingToolBarDetailScreen(
    model: CryptocurrencyModel,
    collapsingState: CollapsingToolbarScaffoldState,
    collapsingToolbarScope: CollapsingToolbarScope,
    onBackClick: () -> Unit
) {
    val textSize = (25 + (60 - 50) * collapsingState.toolbarState.progress).sp
    val textSizeUnder = (12 * collapsingState.toolbarState.progress).sp
    val imageSize = (100 * collapsingState.toolbarState.progress).dp
    IconButton(onClick = { onBackClick() }) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
    }
    with(collapsingToolbarScope) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .pin()
        )
        Column(
            modifier = Modifier
                .road(Alignment.TopCenter, Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = model.imageUrl,
                contentDescription = model.symbol,
                modifier = Modifier.size(imageSize),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.size(1.dp, 14.dp))
            Text(
                text = model.name,
                modifier = Modifier,
                color = Color.White,
                fontSize = textSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = model.symbol,
                modifier = Modifier,
                color = Color.White,
                fontSize = textSizeUnder,
                fontWeight = FontWeight.Normal
            )
        }
    }
}