package com.iwelogic.jedyapp.ui

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.*
import com.iwelogic.ads.AdProvider
import com.iwelogic.jedyapp.navigation.Route
import com.iwelogic.jedyapp.theme.JedyAppTheme
import com.iwelogic.jedyapp.ui.details.MovieDetailsScreen
import com.iwelogic.jedyapp.ui.favourite.FavouriteScreen
import com.iwelogic.jedyapp.ui.movies.MoviesScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var adProvider: AdProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adProvider.init(this)
        enableEdgeToEdge()
        setContent {
            JedyAppTheme {
                val context = this
                val statusBar = MaterialTheme.colorScheme.primaryContainer.toArgb()
                val statusBarTextColor = MaterialTheme.colorScheme.onPrimaryContainer.toArgb()
                context.enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        statusBar,
                        statusBarTextColor
                    ),

                    navigationBarStyle = SystemBarStyle.light(
                        statusBar,
                        statusBarTextColor
                    )
                )
                val navController = rememberNavController()
                navController.saveState()
                Column {
                    NavHost(
                        navController = navController,
                        startDestination = Route.Movies().route,
                        enterTransition = {
                            EnterTransition.None
                        },
                        exitTransition = {
                            ExitTransition.None
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        composable(Route.Movies().route) {
                            MoviesScreen(
                                openDetails = { movie ->
                                    navController.navigate(Route.Details.fromMovie(movie))
                                },
                                openFavorite = {
                                    navController.navigate(Route.Favourite)
                                }
                            )
                        }
                        composable<Route.Favourite> {
                            FavouriteScreen(
                                openDetails = { movie ->
                                    navController.navigate(Route.Details.fromMovie(movie))
                                },
                                onClickBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable<Route.Details> {
                            MovieDetailsScreen(
                                onClickBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}