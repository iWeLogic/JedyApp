package com.iwelogic.jedyapp.data

import android.content.Context
import androidx.room.Room
import com.iwelogic.jedyapp.models.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class DataBaseSource @Inject constructor(@ApplicationContext applicationContext: Context) {

    private var dataBase = Room.databaseBuilder(applicationContext, DataBase::class.java, "favorite")
        .allowMainThreadQueries().build()

    suspend fun insertMovieToFavourite(movie: Movie): Result<Boolean> {
        dataBase.itemDao().insert(movie)
        return Result.success(true)
    }

    suspend fun removeFromFavourite(imdbID: String): Result<Boolean> {
        dataBase.itemDao().removeFromFavourite(imdbID)
        return Result.success(true)
    }
    suspend fun isFavorite(imdbID: String) = dataBase.itemDao().isFavorite(imdbID)

    fun getFavouriteMovies() = dataBase.itemDao().getFavouriteItems()
}