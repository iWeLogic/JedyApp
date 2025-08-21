package com.iwelogic.jedyapp.ui.movies

import androidx.lifecycle.*
import com.iwelogic.jedyapp.data.MoviesRepository
import com.iwelogic.jedyapp.data.base.AppFailure
import com.iwelogic.jedyapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import javax.inject.*

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : BaseViewModel<MoviesState, MoviesIntent, MoviesEvent>(initialState = MoviesState.Loading) {

    init {
        onReload()
    }

    private fun onReload() {
        setState(MoviesState.Loading)
        viewModelScope.launch {
            repository.getMovies("love")
                .onSuccess { result ->
                    setState(MoviesState.Main(result.search ?: listOf()))
                }
                .onFailure { failure ->
                    when (failure) {
                        is AppFailure.UnknownFailure -> setState(MoviesState.Error)
                        else -> setState(MoviesState.Error)
                    }
                }
        }
    }

    override fun handleIntent(intent: MoviesIntent) {
        when (intent) {
            is MoviesIntent.OnClickReload -> onReload()
            is MoviesIntent.OpenDetails -> sendEvent(MoviesEvent.OpenProjectDetails(intent.movie))
        }
    }
}