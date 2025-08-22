package com.iwelogic.jedyapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iwelogic.jedyapp.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mod: Movie)

    @Query("DELETE FROM movies WHERE imdbID = :imdbID")
    suspend fun removeFromFavourite(imdbID: String?)

    @Query("SELECT EXISTS(SELECT * from movies WHERE imdbID = :imdbID)")
    suspend fun isFavorite(imdbID: String): Boolean

    @Query("SELECT * from movies")
    fun getFavouriteItems(): Flow<List<Movie>>

}