package com.rawg.network.ext

import com.google.gson.Gson
import com.rawg.core.Resource
import com.rawg.network.dto.ErrorDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

suspend fun <T, R> safeApiCall(
    default: T,
    apiCall: suspend () -> Response<T>,
    transform: (T) -> R,
): Resource<R> {
    return withContext(Dispatchers.IO) {
        try {
            val response: Response<T> = apiCall()
            if (response.isSuccessful) {
                Resource.Success(data = transform((response.body() ?: default)))
            } else {
                val errorResponse: ErrorDto? = convertErrorBody(response.errorBody())
                Resource.Failure(
                    errorMessage = errorResponse?.error ?: errorResponse?.detail
                    ?: "Something went wrong",
                    responseCode = response.code(),
                )
            }

        } catch (e: Exception) {
            Resource.Failure(errorMessage = "Something went wrong", throwable = e)
        }
    }
}

private fun convertErrorBody(errorBody: ResponseBody?): ErrorDto? {
    return try {
        errorBody?.toString()?.let {
            val gson = Gson()
            return gson.fromJson(it, ErrorDto::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}
