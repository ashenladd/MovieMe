package com.example.movieme.feature.home

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
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState())
    val viewState get() = _viewState.asStateFlow()

    private val _viewEffect = MutableSharedFlow<HomeViewEffect>()
    val viewEffect get() = _viewEffect.asSharedFlow()

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            movieUseCase.getMovieList(1)
                .onStart {
                    _viewEffect.emit(HomeViewEffect.OnLoadingMovies)
                }.collectLatest { it ->
                    when (it.status) {
                        ApiStatus.LOADING -> {
                            _viewEffect.emit(HomeViewEffect.OnLoadingMovies)
                        }

                        ApiStatus.ERROR -> {
                            val errorResponse = it.message
                            _viewEffect.emit(HomeViewEffect.OnError(errorResponse.orEmpty()))
                        }

                        ApiStatus.SUCCESS -> {
                            val data = it.data
                            _viewState.update { state ->
                                state.copy(
                                    popularMovies = data.orEmpty(),
                                    //nanti ganti api service untuk upcoming
                                    recentMovies = data.orEmpty()
                                )
                            }
                            _viewEffect.emit(HomeViewEffect.OnSuccessGetMoviesList(data.orEmpty()))
                        }
                    }
                }
        }
    }

}