package com.iwelogic.jedyapp.ui.movies

import androidx.lifecycle.viewModelScope
import com.iwelogic.ads.AdProvider
import com.iwelogic.jedyapp.data.MoviesRepository
import com.iwelogic.jedyapp.models.ListItem
import com.iwelogic.jedyapp.models.NativeAdItem
import com.iwelogic.jedyapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository,
    val adProvider: AdProvider
) : BaseViewModel<MoviesState, MoviesIntent, MoviesEvent>(initialState = MoviesState()) {

    var searchJob: Job? = null

    init {
        adProvider.loadInterstitial(
            adUnitId = "ca-app-pub-3940256099942544/1033173712")
    }

    private fun onReload(search: String) {
        searchJob = viewModelScope.launch {
            delay(500)
            setState(
                state.value.copy(
                    isLoading = true
                )
            )
            repository.getMovies(search)
                .onSuccess { result ->
                    adProvider.clearNativeAdCache()
                    val items : MutableList<ListItem> = result.search?.toMutableList() ?: mutableListOf()
                    if(items.size > 1) {
                        items.add(1, NativeAdItem(adItemId = "adItemId1"))
                    }
                    if(items.size > 4) {
                        items.add(4, NativeAdItem(adItemId = "adItemId2"))
                    }
                    setState(
                        state.value.copy(
                            error = null,
                            isLoading = false,
                            items = items
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
            is MoviesIntent.OnSearch -> {
                setState(
                    state.value.copy(
                        query = intent.query
                    )
                )
                searchJob?.cancel()
                onReload(intent.query)
            }
            is MoviesIntent.OpenDetails -> {
                adProvider.showInterstitial {
                    sendEvent(MoviesEvent.OpenProjectDetails(intent.movie))
                }
            }
        }
    }
}