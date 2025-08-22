package com.iwelogic.jedyapp.ui.movies

import com.iwelogic.jedyapp.models.ListItem

data class MoviesState(
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val items: List<ListItem>? = null
)