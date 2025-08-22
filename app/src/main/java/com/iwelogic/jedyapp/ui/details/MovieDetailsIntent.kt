package com.iwelogic.jedyapp.ui.details

sealed class MovieDetailsIntent {
    data object OnClickFavorite : MovieDetailsIntent()
}