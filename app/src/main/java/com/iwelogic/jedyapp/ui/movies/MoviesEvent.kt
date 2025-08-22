package com.iwelogic.jedyapp.ui.movies

import com.iwelogic.jedyapp.models.Movie

sealed class MoviesEvent {
    data class OpenProjectDetails(val movie: Movie) : MoviesEvent()
    data object OpenFavorite : MoviesEvent()
}