package com.example.movieme.feature.movie_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movieme.feature.theme.TextAppearanceCormorantMovieMeTitle2

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MoviePoster(
    modifier: Modifier = Modifier,
    urlPath: String?,
    movieTitle: String?,
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            GlideImage(
                modifier = Modifier
                    .height(260.dp)
                    .width(180.dp)
                    .shadow(elevation = 4.dp),
                model = "https://image.tmdb.org/t/p/original${urlPath}",
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movieTitle.toString(),
                style = TextAppearanceCormorantMovieMeTitle2
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}