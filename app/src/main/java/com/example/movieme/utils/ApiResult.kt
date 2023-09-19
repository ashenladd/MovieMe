package com.example.movieme.utils

import java.lang.Exception

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING,
}

sealed class ApiResult<T>(val status: ApiStatus, val data: T?, val message: String?) {
    data class Success<T>(val _data: T) : ApiResult<T>(
        status = ApiStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error<T>(val exception: String) : ApiResult<T>(
        status = ApiStatus.ERROR,
        data = null,
        message = exception
    )

    data class Loading<T>(val _data: T? = null) : ApiResult<T>(
        status = ApiStatus.LOADING,
        data = _data,
        message = null
    )
}