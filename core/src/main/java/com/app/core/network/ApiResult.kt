package com.app.core.network

sealed class ApiResult<out R> {

    data class Loading(val data: Nothing? = null) : ApiResult<Nothing>()
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Throwable) : ApiResult<Nothing>()
}
