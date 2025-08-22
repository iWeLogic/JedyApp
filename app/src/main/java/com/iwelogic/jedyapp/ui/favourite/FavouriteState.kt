package com.iwelogic.jedyapp.ui.favourite

import com.iwelogic.jedyapp.models.Movie

data class FavouriteState(
    val isLoading: Boolean = false,
    val movies: List<Movie>? = null
)