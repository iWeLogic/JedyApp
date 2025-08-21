package com.iwelogic.jedyapp.models

import com.google.gson.annotations.SerializedName

data class Movies(



    @field:SerializedName("totalResults")
    val totalResults: String? = null,

    @field:SerializedName("Search")
    val search: List<Movie>? = null
) : BaseResponse()

