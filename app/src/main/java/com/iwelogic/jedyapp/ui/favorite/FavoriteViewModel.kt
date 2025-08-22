package com.iwelogic.jedyapp.ui.favorite

import androidx.lifecycle.viewModelScope
import com.iwelogic.jedyapp.data.MoviesRepository
import com.iwelogic.jedyapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : BaseViewModel<FavoriteState, FavoriteIntent, FavoriteEvent>(initialState = FavoriteState()) {

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

    override fun handleIntent(intent: FavoriteIntent) {
        when (intent) {
            is FavoriteIntent.OpenDetails -> sendEvent(FavoriteEvent.OpenProjectDetails(intent.movie))
        }
    }
}