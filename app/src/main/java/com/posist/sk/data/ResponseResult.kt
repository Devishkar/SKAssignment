package com.posist.sk.data

sealed class ResponseResult<T> {
    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error<T>(val message: String) : ResponseResult<T>()
}
