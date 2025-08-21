package com.iwelogic.jedyapp.ui.details

sealed class MovieDetailsEvent {
    data object OpenProjectDetails : MovieDetailsEvent()
}