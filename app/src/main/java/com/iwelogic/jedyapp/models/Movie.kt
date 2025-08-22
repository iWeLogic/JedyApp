package com.iwelogic.jedyapp.models

import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "movies")
@Serializable
data class Movie(

    @ColumnInfo(name = "type")
    @field:SerializedName("Type")
    val type: String? = null,

    @ColumnInfo(name = "year")
    @field:SerializedName("Year")
    val year: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "imdbID")
    @field:SerializedName("imdbID")
    val imdbID: String,

    @ColumnInfo(name = "poster")
    @field:SerializedName("Poster")
    val poster: String? = null,

    @ColumnInfo(name = "title")
    @field:SerializedName("Title")
    val title: String
    
) : ListItem {

    override val id: String
        get() = imdbID
}
