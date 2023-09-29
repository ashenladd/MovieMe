package com.example.movieme.feature.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class MoviesViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow<MoviesViewState>(MoviesViewState())
    val viewState get() = _viewState.asStateFlow()

    private val _viewEffect = MutableSharedFlow<MoviesViewEffect>()
    val viewEffect get() = _viewEffect.asSharedFlow()

    init {
        getMovies(_viewState.value.page)
    }

    fun onEvent(event: MoviesViewEvent) {
        when (event) {
            is MoviesViewEvent.NextPage -> {
                _viewState.update {
                    it.copy(
                        page = _viewState.value.page + 1
                    )
                }
                getMovies(_viewState.value.page)
            }

            is MoviesViewEvent.PrevPage -> {
                _viewState.update {
                    it.copy(
                        page = _viewState.value.page - 1
                    )
                }
                getMovies(_viewState.value.page)
            }

        }
    }

    private fun getMovies(page: Int) {
        viewModelScope.launch {
            movieUseCase.getMovieList(page)
                .onStart {
                    _viewEffect.emit(MoviesViewEffect.OnLoadingMovies)
                }.collectLatest { it ->
                    when (it.status) {
                        ApiStatus.LOADING -> {
                            _viewEffect.emit(MoviesViewEffect.OnLoadingMovies)
                        }

                        ApiStatus.ERROR -> {
                            val errorResponse = it.message
                            _viewEffect.emit(MoviesViewEffect.OnError(errorResponse.orEmpty()))
                        }

                        ApiStatus.SUCCESS -> {
                            val data = it.data
                            _viewState.update { state ->
                                state.copy(
                                    movies = data.orEmpty(),
                                    page = page
                                )
                            }
                            _viewEffect.emit(MoviesViewEffect.OnSuccessGetMoviesList(data.orEmpty()))
                        }
                    }
                }
        }
    }
}