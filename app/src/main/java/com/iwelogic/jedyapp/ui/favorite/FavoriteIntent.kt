package com.iwelogic.jedyapp.ui.favorite

import com.iwelogic.jedyapp.models.Movie

sealed class FavoriteIntent {
    data class OpenDetails(val movie: Movie) : FavoriteIntent()
}