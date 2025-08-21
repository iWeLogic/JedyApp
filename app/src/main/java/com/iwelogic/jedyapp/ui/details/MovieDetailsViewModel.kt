package com.iwelogic.jedyapp.ui.details

import androidx.lifecycle.*
import androidx.navigation.toRoute
import com.iwelogic.jedyapp.navigation.Screen
import com.iwelogic.jedyapp.ui.base.BaseViewModel
import com.iwelogic.projects.presentation.ui.details.MovieDetailsIntent
import dagger.hilt.android.lifecycle.*
import javax.inject.*

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedState: SavedStateHandle,
) : BaseViewModel<MovieDetailsState, MovieDetailsIntent, MovieDetailsEvent>(MovieDetailsState(movie = savedState.toRoute<Screen.Details>().convertToMovie())) {

    override fun handleIntent(intent: MovieDetailsIntent) {

    }
}