package com.iwelogic.jedyapp.data.base

import com.google.gson.Gson
import com.iwelogic.jedyapp.models.BaseResponse
import kotlinx.coroutines.CancellationException
import retrofit2.Response
import java.net.UnknownHostException

open class BaseDataSource {

    suspend fun <T> getResponse(request: suspend () -> Response<T>): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return try {
                    val response = result.body() as BaseResponse
                    if (response.response == "True") {
                        Result.success(result.body()!!)
                    } else {
                        Result.failure(AppFailure.ResponseFailure(message = response.error))
                    }
                } catch (e: Exception) {
                    when (e) {
                        is CancellationException -> throw (e)
                        else -> Result.failure(e)
                    }
                }
            } else {
                return try {
                    val responseError = Gson().fromJson(result.errorBody()?.string(), BaseResponse::class.java)
                    Result.failure(AppFailure.ResponseFailure(message = responseError.error))
                } catch (e: Exception) {
                    when (e) {
                        is CancellationException -> throw (e)
                        else -> Result.failure(e)
                    }
                }
            }
        } catch (e: Throwable) {
            when (e) {
                is UnknownHostException -> Result.failure(e)
                is CancellationException -> throw (e)
                else -> Result.failure(UnknownError(e.message))
            }
        }
    }
}

