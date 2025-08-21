package com.iwelogic.jedyapp.ui.movies

import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.*
import androidx.lifecycle.*
import androidx.lifecycle.compose.*
import com.iwelogic.jedyapp.models.Movie
import com.iwelogic.jedyapp.ui.views.ErrorPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(openDetails: (Movie) -> Unit, viewModel: MoviesViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(Unit) {
        viewModel.event
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect { uiEffect ->
                when (uiEffect) {
                    is MoviesEvent.OpenProjectDetails -> openDetails(uiEffect.movie)
                }
            }
    }

    when (val state: MoviesState = viewModel.state.collectAsStateWithLifecycle().value) {
        is MoviesState.Loading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
        is MoviesState.Error -> {
            ErrorPage(modifier = Modifier.fillMaxSize()) {
                viewModel.handleIntent(MoviesIntent.OnClickReload)
            }
        }
        is MoviesState.Main -> {
            PullToRefreshBox(
                isRefreshing = false,
                onRefresh = {
                    viewModel.handleIntent(MoviesIntent.OnClickReload)
                },
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(16.dp),
                    reverseLayout = false,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    flingBehavior = ScrollableDefaults.flingBehavior(),
                    userScrollEnabled = true,
                ) {
                    items(state.movies, key = { movie -> movie.title }) {
                        MovieItem(item = it, modifier = Modifier.fillMaxWidth()) { movie ->
                            viewModel.handleIntent(MoviesIntent.OpenDetails(movie))
                        }
                    }
                }
            }
        }
    }
}