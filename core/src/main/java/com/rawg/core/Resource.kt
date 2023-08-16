package com.rawg.core

sealed class Resource<out T> {
    class Idle<out T> : Resource<T>()
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(
        val errorMessage: String? = null,
        val throwable: Throwable? = null,
        val responseCode: Int? = null,
    ) : Resource<T>()
}
