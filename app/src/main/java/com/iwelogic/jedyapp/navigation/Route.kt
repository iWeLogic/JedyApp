package com.iwelogic.jedyapp.navigation

import com.iwelogic.jedyapp.models.Movie
import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data class Movies(
        val route: String = "movies"
    ) : Route()

    @Serializable
    data object Favourite : Route()

    @Serializable
    data class Details(
        val type: String? = null,
        val year: String? = null,
        val imdbID: String,
        val poster: String? = null,
        val title: String
    ) : Route() {
        fun toMovie(): Movie {
            return Movie(
                type = type,
                year = year,
                imdbID = imdbID,
                poster = poster,
                title = title
            )
        }

        companion object Companion {
            fun fromMovie(movie: Movie): Details {
                return Details(
                    type = movie.type,
                    year = movie.year,
                    imdbID = movie.imdbID,
                    poster = movie.poster,
                    title = movie.title,
                )
            }
        }
    }
}