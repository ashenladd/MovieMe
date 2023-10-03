package com.example.movieme.feature.movie_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieme.R
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.feature.movie_detail.component.CustomButton
import com.example.movieme.feature.movie_detail.component.MovieMoreDetailRow
import com.example.movieme.feature.movie_detail.component.MoviePoster
import com.example.movieme.feature.movie_detail.component.RatingStars
import com.example.movieme.feature.movie_detail.component.TransparentTopAppBar
import com.example.movieme.feature.theme.TextAppearanceCormorantMovieMeTitle2
import com.example.movieme.feature.theme.TextAppearanceLexendMovieMeBody1
import com.example.movieme.feature.theme.TextAppearanceLexendMovieMeSubtitle1
import com.example.movieme.feature.utils.extractDatePartsFromDate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    listener: MovieDetailListener? = null,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val stateMovie by viewModel.viewState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            viewModel.onEvent(
                MovieDetailViewEvent.RefreshPage(
                    viewModel.currentId ?: -1
                )
            )
        })
    val dateParts = extractDatePartsFromDate(stateMovie.releaseDate.toString())

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
            TransparentTopAppBar { listener?.navigateBack() }
        }
    ) {
        Column(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
                .padding(it)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            MoviePoster(
                modifier = Modifier.fillMaxWidth(),
                urlPath = stateMovie.posterPath,
                movieTitle = stateMovie.title
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    RatingStars(
                        rating10 = stateMovie.voteAverage ?: 0.0
                    )
                    Text(
                        text = "${stateMovie.voteCount} Rating",
                        style = TextAppearanceLexendMovieMeSubtitle1.copy(color = Color.Gray)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "More"
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.label_overview),
                style = TextAppearanceCormorantMovieMeTitle2
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stateMovie.overview.orEmpty(),
                style = TextAppearanceLexendMovieMeBody1,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(24.dp))
            CustomButton(text = stringResource(R.string.label_watch_now))
            Spacer(modifier = Modifier.height(24.dp))
            MovieMoreDetailRow(
                textYear = dateParts?.year.toString(),
                textDate = "${dateParts?.day} ${dateParts?.month}",
                textLanguageTitle = stateMovie.language.toString(),
                textLanguageSubtitle = "English"
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreenContent(
    movieContent: MovieModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TransparentTopAppBar {}
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            MoviePoster(
                modifier = Modifier.fillMaxWidth(),
                urlPath = movieContent.backdropPath,
                movieTitle = movieContent.title
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    RatingStars(
                        rating10 = movieContent.voteAverage ?: 0.0
                    )
                    Text(
                        text = "${movieContent.voteCount} Rating",
                        style = TextAppearanceLexendMovieMeSubtitle1.copy(color = Color.Gray)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "More"
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.label_overview),
                style = TextAppearanceCormorantMovieMeTitle2
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = movieContent.overview.orEmpty(),
                style = TextAppearanceLexendMovieMeBody1
            )
            Spacer(modifier = Modifier.height(24.dp))
            CustomButton(text = stringResource(R.string.label_watch_now))
        }
    }
}

@Preview
@Composable
fun PreviewMovieDetailScreen() {
    MovieDetailScreenContent(
        movieContent = MovieModel(
            title = "Call",
            releaseDate = "24 Mei 2003",
            popularity = 1231.0,
            voteAverage = 8.0,
            voteCount = 1222,
            overview = "Lorem awdjiawjdoiawjdoiawj aiwodjoiawjdio aowijdoaiw jdioawj",
            backdropPath = "H6j5smdpRqP9a8UnhWp6zfl0SC.jpg",
            posterPath = "H6j5smdpRqP9a8UnhWp6zfl0SC.jpg"
        )
    )
}



