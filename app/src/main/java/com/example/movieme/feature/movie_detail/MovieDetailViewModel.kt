package com.example.movieme.feature.movie_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.domain.usecase.MovieUseCase
import com.example.movieme.utils.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private var movieUseCase: MovieUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow(MovieModel())
    val viewState get() = _viewState.asStateFlow()

    private val _viewEffect = MutableSharedFlow<MovieDetailViewEffect>()
    val viewEffect get() = _viewEffect.asSharedFlow()

    private var currentId: Long? = null

    init {

    }

    fun loadMovie(movieId: Long) {
        Log.d("ViewModelMovieDetail","Movie ID LoadMovie = $movieId")
        getMovie(movieId)
    }

    private fun getMovie(movieId: Long) {
        Log.d("ViewModelMovieDetail","Movie ID On Start= $movieId")
        viewModelScope.launch {
            movieUseCase.getMovie(movieId)
                .onStart {
                    _viewEffect.emit(MovieDetailViewEffect.OnLoadingMovies)
                    Log.d("ViewModelMovieDetail","Movie Loading $movieId")
                }.collectLatest {
                    when (it.status) {
                        ApiStatus.LOADING -> {
                            _viewEffect.emit(MovieDetailViewEffect.OnLoadingMovies)
                            Log.d("ViewModelMovieDetail","Movie Status Loading = ${it.status}")
                            Log.d("ViewModelMovieDetail","Movie ID Loading = ${it.data?.id}")
                        }

                        ApiStatus.ERROR -> {
                            val errorResponse = it.message
                            _viewEffect.emit(MovieDetailViewEffect.OnError(errorResponse.orEmpty()))
                        }

                        ApiStatus.SUCCESS -> {
                            val data = it.data
                            Log.d("ViewModelMovieDetail","Movie ID Success = ${it.data?.id}")
                            currentId = data?.id
                            _viewState.update { state ->
                                state.copy(
                                    id = data?.id,
                                    title = data?.title,
                                    overview = data?.overview,
                                    posterPath = data?.posterPath,
                                    backdropPath = data?.backdropPath,
                                    releaseDate = data?.releaseDate,
                                    voteAverage = data?.voteAverage,
                                    popularity = data?.popularity,
                                    voteCount = data?.voteCount
                                )
                            }
                            _viewEffect.emit(MovieDetailViewEffect.OnSuccessGetMovie)
                        }
                    }
                }
        }
    }
}
