package com.app.core.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object NetworkCall {
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> T,
    ): ApiResult<T> {
        return withContext(dispatcher) {
            try {
                ApiResult.Success(apiCall.invoke())
            } catch (t: Throwable) {
                ApiResult.Error(t)
            }
        }
    }
}
