package com.example.movieme.feature.movie_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movieme.R
import com.example.movieme.feature.theme.TextAppearanceCormorantMovieMeTitle2
import com.example.movieme.feature.theme.TextAppearanceLexendMovieMeBody1
import com.example.movieme.feature.theme.TextAppearanceLexendMovieMeSubtitle1
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val stateMovie by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.viewEffect.collectLatest {
            when (it) {
                is MovieDetailViewEffect.OnError -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(it.message)
                    }
                }

                else -> {}
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TransparentTopAppBar()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            MoviePoster(
                modifier = Modifier.fillMaxWidth(),
                urlPath = stateMovie.backdropPath,
                movieTitle = stateMovie.title
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    RatingStars(rating10 = stateMovie.voteAverage ?: 0.0)
                    Text(
                        text = "${stateMovie.voteCount} Rating",
                        style = TextAppearanceLexendMovieMeSubtitle1
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "More"
                )
            }

            Text(
                text = stateMovie.title.orEmpty(),
                style = TextAppearanceCormorantMovieMeTitle2
            )
            Text(
                text = stateMovie.overview.orEmpty(),
                style = TextAppearanceLexendMovieMeBody1
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TransparentTopAppBar() {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            Color.Transparent
        ),
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }
    )
}

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
                model = "https://image.tmdb.org/t/p/original${urlPath}",
                contentDescription = null,
            )
            Text(
                text = movieTitle.toString(),
                style = TextAppearanceCormorantMovieMeTitle2
            )
        }
    }
}


@Preview
@Composable
fun PreviewMoviePoster() {
    MoviePoster(
        urlPath = "H6j5smdpRqP9a8UnhWp6zfl0SC",
        movieTitle = "Blue Bittle"
    )
}


@Composable
fun RatingStars(
    modifier: Modifier = Modifier,
    rating10: Double,
    maxRating: Int = 5,
    ratingColor: Color = Color(0xFFFDD147),
) {
    val rating5 = rating10 / 2
    val fullStars = rating5.toInt()

    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            val iconColor = when {
                i <= fullStars -> ratingColor
                else -> Color.Gray
            }
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewRating() {
    RatingStars(rating10 = 4.0)
}

