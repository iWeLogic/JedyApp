package com.iwelogic.jedyapp.ui.movies

import com.iwelogic.jedyapp.models.Movie

sealed class MoviesIntent {
    data class OpenDetails(val movie: Movie) : MoviesIntent()
    data object OnClickReload : MoviesIntent()
}