package com.iwelogic.jedyapp.ui.movies

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.iwelogic.ads.*
import com.iwelogic.jedyapp.models.Movie
import com.iwelogic.jedyapp.models.NativeAdItem
import com.iwelogic.jedyapp.theme.JedyAppTheme
import com.iwelogic.jedyapp.ui.views.*
import com.iwelogic.jedyapp.R

@Composable
fun MoviesScreen(openDetails: (Movie) -> Unit, openFavorite: () -> Unit, viewModel: MoviesViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val state: MoviesState = viewModel.state.collectAsStateWithLifecycle().value
    LaunchedEffect(Unit) {
        viewModel.event
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect { uiEffect ->
                when (uiEffect) {
                    is MoviesEvent.OpenProjectDetails -> openDetails(uiEffect.movie)
                    MoviesEvent.OpenFavorite -> openFavorite()
                }
            }
    }

    MoviesScreenView(
        state = state,
        onClickReload = { viewModel.handleIntent(MoviesIntent.OnClickReload) },
        onClickFavorite = { viewModel.handleIntent(MoviesIntent.OnClickFavorite) },
        onClickDetails = { movie -> viewModel.handleIntent(MoviesIntent.OnClickMovie(movie)) },
        onSearch = { query -> viewModel.handleIntent(MoviesIntent.OnSearch(query)) },
        adProvider = viewModel.adProvider
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreenView(
    state: MoviesState,
    onClickReload: () -> Unit,
    onClickFavorite: () -> Unit,
    onSearch: (String) -> Unit,
    onClickDetails: (Movie) -> Unit,
    adProvider: AdProvider
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)),
                title = {
                    Text(
                        stringResource(R.string.app_name),
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
                actions = {
                    IconButton(onClick = {
                        onClickFavorite()
                    }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                SearchBox(
                    query = state.query,
                    onQueryChange = {
                        onSearch(it)
                    }
                )
                if (state.isLoading) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                } else if (!state.error.isNullOrEmpty()) {
                    ErrorPage(modifier = Modifier.fillMaxSize(), error = state.error) {
                        onClickReload()
                    }
                } else {
                    val movies = state.items
                    if (movies.isNullOrEmpty()) {
                        EmptyPage(
                            modifier = Modifier.fillMaxSize(),
                            text = stringResource(R.string.no_movies_search)
                        )
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

                            items(state.items, key = { item -> item.id }) { item ->
                                if (item is Movie) {
                                    MovieItem(item = item, modifier = Modifier.fillMaxWidth()) { movie ->
                                        onClickDetails(movie)
                                    }
                                } else if (item is NativeAdItem) {
                                    adProvider.NativeAdComposable(
                                        "ca-app-pub-3940256099942544/2247696110",
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    JedyAppTheme {
        MoviesScreenView(
            state = MoviesState(
                isLoading = false,
                error = null,
                items = listOf(
                    Movie("movie", "1981", "tt0082681", "test", "Test title first"),
                    Movie("movie", "1981", "tt0082682", "test", "Test title second"),
                )
            ),
            onClickReload = {},
            onClickFavorite = {},
            onClickDetails = {},
            onSearch = {},
            adProvider = MockAdProvider
        )
    }
}