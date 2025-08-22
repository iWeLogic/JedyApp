package com.iwelogic.jedyapp.data

import com.iwelogic.jedyapp.models.Movie
import com.iwelogic.jedyapp.models.Movies
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val httpDataSource: HttpDataSource, private val dataBaseSource: DataBaseSource) {

    suspend fun getMovies(@Query("s") searchText: String): Result<Movies> {
        return httpDataSource.getMovies(searchText)
    }

    suspend fun insertMovieToFavourite(movie: Movie): Result<Boolean> {
        return dataBaseSource.insertMovieToFavourite(movie)
    }

    suspend fun removeFromFavourite(imdbID: String): Result<Boolean> {
        return dataBaseSource.removeFromFavourite(imdbID)
    }

    suspend fun isFavorite(imdbID: String): Boolean {
        return dataBaseSource.isFavorite(imdbID)
    }

    fun getFavouriteMovies(): Flow<List<Movie>> {
        return dataBaseSource.getFavouriteMovies()
    }
}