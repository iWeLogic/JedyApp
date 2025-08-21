package com.iwelogic.jedyapp.data

import com.iwelogic.jedyapp.models.Movies
import retrofit2.http.Query
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val httpDataSource: HttpDataSource) {

    suspend fun getMovies(@Query("s") searchText: String): Result<Movies> {
        return httpDataSource.getMovies(searchText)
    }
}