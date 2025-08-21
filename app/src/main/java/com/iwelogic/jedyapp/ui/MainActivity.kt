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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.iwelogic.jedyapp.navigation.Screen
import com.iwelogic.jedyapp.theme.JedyAppTheme
import com.iwelogic.jedyapp.ui.details.MovieDetailsScreen
import com.iwelogic.jedyapp.ui.movies.MoviesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        startDestination = Screen.Movies().route,
                        enterTransition = {
                            EnterTransition.None
                        },
                        exitTransition = {
                            ExitTransition.None
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        composable(Screen.Movies().route) {
                            MoviesScreen(
                                openDetails = { movie ->
                                    navController.navigate(Screen.Details.convertFromMovie(movie))
                                }
                            )
                        }
                        composable<Screen.Details> {
                            MovieDetailsScreen (onClickBack = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JedyAppTheme {
        Greeting("Android")
    }
}