package com.iwelogic.jedyapp.ui.movies

import androidx.lifecycle.viewModelScope
import com.iwelogic.jedyapp.data.MoviesRepository
import com.iwelogic.jedyapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : BaseViewModel<MoviesState, MoviesIntent, MoviesEvent>(initialState = MoviesState()) {

    var searchJob: Job? = null

    private fun onReload(search: String) {
        searchJob = viewModelScope.launch {
            delay(500)
            setState(state.value.copy(
                isLoading = true
            ))
            repository.getMovies(search)
                .onSuccess { result ->
                    setState(
                        state.value.copy(
                            error = null,
                            isLoading = false,
                            movies = result.search
                        )
                    )
                }
                .onFailure { failure ->
                    setState(
                        state.value.copy(
                            isLoading = false,
                            error = failure.message,
                        )
                    )
                }
        }
    }

    override fun handleIntent(intent: MoviesIntent) {
        when (intent) {
            is MoviesIntent.OnClickReload -> onReload("")
            is MoviesIntent.OnSearch ->  {
                setState(
                    state.value.copy(
                        query = intent.query
                    )
                )
                searchJob?.cancel()
                onReload(intent.query)
            }
            is MoviesIntent.OpenDetails -> sendEvent(MoviesEvent.OpenProjectDetails(intent.movie))
        }
    }
}