package com.iwelogic.jedyapp.ui.favourite

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.iwelogic.jedyapp.models.Movie
import com.iwelogic.jedyapp.ui.views.ErrorPage
import com.iwelogic.jedyapp.ui.views.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(openDetails: (Movie) -> Unit, onClickBack: () -> Unit, viewModel: FavouriteViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val state: FavouriteState = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.event
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect { uiEffect ->
                when (uiEffect) {
                    is FavouriteEvent.OpenProjectDetails -> openDetails(uiEffect.movie)
                }
            }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)),
                title = {
                    Text(
                        "JedyApp",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        onClickBack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                if (state.isLoading) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                } else if (!state.error.isNullOrEmpty()) {
                    ErrorPage(modifier = Modifier.fillMaxSize(), error = state.error) {
                        viewModel.handleIntent(FavouriteIntent.OnClickReload)
                    }
                } else {
                    val movies = state.movies
                    if (movies.isNullOrEmpty()) {
                        Text("Empty")
                    } else {
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
                            items(state.movies, key = { movie -> movie.imdbID }) {
                                MovieItem(item = it, modifier = Modifier.fillMaxWidth()) { movie ->
                                    viewModel.handleIntent(FavouriteIntent.OpenDetails(movie))
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}