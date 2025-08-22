package com.iwelogic.jedyapp.ui.favorite

import com.iwelogic.jedyapp.models.Movie

sealed class FavoriteEvent {
    data  class OpenProjectDetails (val movie: Movie): FavoriteEvent()
}