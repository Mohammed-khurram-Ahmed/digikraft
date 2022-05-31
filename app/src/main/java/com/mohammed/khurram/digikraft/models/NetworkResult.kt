package com.mohammed.khurram.digikraft.models


sealed class NetworkResult<T : Any> {
    class Success<T: Any>(val data: T) : NetworkResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class Loading<T: Any>() : NetworkResult<T>()
}