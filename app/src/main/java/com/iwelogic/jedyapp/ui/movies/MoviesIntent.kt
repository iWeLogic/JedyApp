package com.iwelogic.jedyapp.ui.movies

import com.iwelogic.jedyapp.models.Movie

sealed class MoviesIntent {
    data class OnClickMovie(val movie: Movie) : MoviesIntent()
    data class OnSearch(val query: String) : MoviesIntent()
    data object OnClickReload : MoviesIntent()
    data object OnClickFavorite : MoviesIntent()
}