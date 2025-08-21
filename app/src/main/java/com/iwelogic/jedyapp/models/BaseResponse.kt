package com.iwelogic.jedyapp.models

import com.google.gson.annotations.SerializedName

open class BaseResponse(

    @field:SerializedName("Response")
    val response: String? = null,

    @field:SerializedName("Error")
    val error: String? = null,
)