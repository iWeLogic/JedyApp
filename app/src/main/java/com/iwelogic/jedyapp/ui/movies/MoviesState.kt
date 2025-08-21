package com.iwelogic.jedyapp.ui.movies

import com.iwelogic.jedyapp.models.Movie

data class MoviesState(
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val movies: List<Movie>? = null
)