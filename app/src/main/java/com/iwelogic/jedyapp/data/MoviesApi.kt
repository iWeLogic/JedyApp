package com.iwelogic.jedyapp.data

import com.iwelogic.jedyapp.models.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("/")
    suspend fun getMovies(@Query("s") searchText: String): Response<Movies>
}