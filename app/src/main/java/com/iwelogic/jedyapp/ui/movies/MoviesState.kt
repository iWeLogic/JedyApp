package com.iwelogic.jedyapp.ui.movies

import com.iwelogic.jedyapp.models.Movie

sealed class MoviesState {
    data object Loading : MoviesState()
    data object Error : MoviesState()
    data class Main(val movies: List<Movie>) : MoviesState()
}