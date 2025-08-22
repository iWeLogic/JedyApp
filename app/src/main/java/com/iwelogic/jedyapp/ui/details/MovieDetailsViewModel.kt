package com.iwelogic.jedyapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.iwelogic.jedyapp.data.MoviesRepository
import com.iwelogic.jedyapp.navigation.Route
import com.iwelogic.jedyapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedState: SavedStateHandle,
    val moviesRepository: MoviesRepository
) : BaseViewModel<MovieDetailsState, MovieDetailsIntent, MovieDetailsEvent>(
    MovieDetailsState(
        movie = savedState.toRoute<Route.Details>().toMovie()
    )
) {

    var favoriteJob: Job? = null
    val movie = savedState.toRoute<Route.Details>().toMovie()

    init {
        onReload()
    }

    fun onReload() {
        viewModelScope.launch {
            setState(state.value.copy(isLoading = true))
            val isFavorite = moviesRepository.isFavorite(movie.imdbID)
            setState(
                state.value.copy(
                    isLoading = false,
                    isFavorite = isFavorite
                )
            )
        }
    }

    override fun handleIntent(intent: MovieDetailsIntent) {
        when (intent) {
            MovieDetailsIntent.OnClickFavorite -> {
                if (!(favoriteJob?.isActive ?: false)) {
                    favoriteJob = viewModelScope.launch {
                        setState(state.value.copy(isLoading = true))
                        if (state.value.isFavorite) {
                            moviesRepository.removeFromFavourite(movie.imdbID)
                            setState(state.value.copy(isLoading = false, isFavorite = false))
                        } else {
                            moviesRepository.insertMovieToFavourite(movie)
                            setState(state.value.copy(isLoading = false, isFavorite = true))
                        }
                    }
                }
            }
        }
    }
}