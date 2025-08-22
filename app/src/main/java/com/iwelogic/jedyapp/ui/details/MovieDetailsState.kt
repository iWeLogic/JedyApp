package com.iwelogic.jedyapp.ui.details

import com.iwelogic.jedyapp.models.Movie

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val movie: Movie
)