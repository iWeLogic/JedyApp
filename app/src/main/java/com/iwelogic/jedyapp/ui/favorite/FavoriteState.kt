package com.iwelogic.jedyapp.ui.favorite

import com.iwelogic.jedyapp.models.Movie

data class FavoriteState(
    val isLoading: Boolean = false,
    val movies: List<Movie>? = null
)