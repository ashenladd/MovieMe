package com.example.movieme.feature.movie_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.movieme.R
import com.example.movieme.feature.theme.TextAppearanceLexendMovieMeCaption1
import com.example.movieme.feature.theme.TextAppearanceLexendMovieMeSubtitle1
import com.example.movieme.feature.theme.TextAppearanceLexendMovieMeTitle3
import java.util.Locale

@Composable
fun MovieMoreDetail(
    modifier: Modifier = Modifier,
    textCaption: String = "Released",
    textTitle: String = "2014",
    textSubtitle: String = "28 October",
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = textCaption.uppercase(),
            style = TextAppearanceLexendMovieMeCaption1.copy(color = Color.Gray),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = textTitle.uppercase(Locale.getDefault()),
            style = TextAppearanceLexendMovieMeTitle3,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = textSubtitle,
            style = TextAppearanceLexendMovieMeSubtitle1,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun MovieMoreDetailRow(
    modifier: Modifier = Modifier,
    textYear: String,
    textDate: String,
    textLanguageTitle: String,
    textLanguageSubtitle: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MovieMoreDetail(
            modifier = Modifier.weight(1f),
            textCaption = stringResource(R.string.label_released),
            textTitle = textYear,
            textSubtitle = textDate
        )
        Divider(
            modifier = Modifier
                .width(1.dp)
                .height(48.dp)
                .align(Alignment.CenterVertically),
            color = Color.Gray,
        )
        MovieMoreDetail(
            modifier = Modifier.weight(1f),
            textCaption = "Language",
            textTitle = textLanguageTitle,
            textSubtitle = textLanguageSubtitle
        )
    }
}
