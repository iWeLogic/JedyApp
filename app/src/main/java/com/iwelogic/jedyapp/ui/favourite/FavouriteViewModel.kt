package com.iwelogic.jedyapp.ui.favourite

import androidx.lifecycle.viewModelScope
import com.iwelogic.jedyapp.data.MoviesRepository
import com.iwelogic.jedyapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : BaseViewModel<FavouriteState, FavouriteIntent, FavouriteEvent>(initialState = FavouriteState()) {

    init {
        onReload()
    }

    private fun onReload() {
        viewModelScope.launch {
            setState(state.value.copy(isLoading = true))
            moviesRepository.getFavouriteMovies().collect { result ->
                setState(
                    state.value.copy(
                        isLoading = false,
                        movies = result
                    )
                )
            }
        }
    }

    override fun handleIntent(intent: FavouriteIntent) {
        when (intent) {
            is FavouriteIntent.OpenDetails -> sendEvent(FavouriteEvent.OpenProjectDetails(intent.movie))
        }
    }
}