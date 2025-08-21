package com.iwelogic.jedyapp.models

import com.google.gson.annotations.SerializedName

data class Movie(

    @field:SerializedName("Type")
    val type: String? = null,

    @field:SerializedName("Year")
    val year: String? = null,

    @field:SerializedName("imdbID")
    val imdbID: String,

    @field:SerializedName("Poster")
    val poster: String? = null,

    @field:SerializedName("Title")
    val title: String
)
