package com.iwelogic.jedyapp.ui.favourite

import com.iwelogic.jedyapp.models.Movie

sealed class FavouriteEvent {
    data  class OpenProjectDetails (val movie: Movie): FavouriteEvent()
}