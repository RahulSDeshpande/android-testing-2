package com.mojolabs.androidtesting2.util

enum class ApiStatus {
    LOADING,
    SUCCESS,
    ERROR
}

data class ApiResource<out T>(
    val status: ApiStatus,
    val data: T?,
    val message: String?
) {
    companion object {

        fun <T> loading(data: T? = null): ApiResource<T> {
            return ApiResource(
                status = ApiStatus.LOADING,
                data = data,
                message = null
            )
        }

        fun <T> success(data: T?): ApiResource<T> {
            return ApiResource(
                status = ApiStatus.SUCCESS,
                data = data,
                message = null
            )
        }

        fun <T> error(msg: String, data: T? = null): ApiResource<T> {
            return ApiResource(
                status = ApiStatus.ERROR,
                data = data,
                message = msg
            )
        }
    }
}
