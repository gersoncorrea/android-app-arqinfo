package com.app.core.network

class Resource<T>(
      val mStatus: Status,
      val mData: T? = null,
      val mMessage: String?,
      val mError: Throwable? = null
) {

    enum class Status {
        SUCCESS, ERROR, LOADING, VALIDATION
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(error: Throwable): Resource<T> {
            return Resource(Status.ERROR, null, null, error)
        }

        fun <T> loading(data: T): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> validation(data: T): Resource<T> {
            return Resource(Status.VALIDATION, data, null, null)
        }
    }
}