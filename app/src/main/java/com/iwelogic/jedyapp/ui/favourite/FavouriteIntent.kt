package com.iwelogic.jedyapp.ui.favourite

import com.iwelogic.jedyapp.models.Movie

sealed class FavouriteIntent {
    data class OpenDetails(val movie: Movie) : FavouriteIntent()
    data object OnClickReload : FavouriteIntent()
}