package com.iwelogic.jedyapp.data

import com.iwelogic.jedyapp.data.base.BaseDataSource
import com.iwelogic.jedyapp.models.Movies
import javax.inject.Inject

class HttpDataSource @Inject constructor(private val api: MoviesApi) : BaseDataSource() {

    suspend fun getMovies(searchText: String): Result<Movies> {
        return getResponse(request = { api.getMovies(searchText) })
    }
}