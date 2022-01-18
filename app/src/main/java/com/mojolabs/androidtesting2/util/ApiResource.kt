package com.mojolabs.androidtesting2.util

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}

data class ApiResource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {

        fun <T> loading(data: T?): ApiResource<T> {
            return ApiResource(
                status = Status.LOADING,
                data = data,
                message = null
            )
        }

        fun <T> success(data: T?): ApiResource<T> {
            return ApiResource(
                status = Status.SUCCESS,
                data = data,
                message = null
            )
        }

        fun <T> error(msg: String, data: T? = null): ApiResource<T> {
            return ApiResource(
                status = Status.ERROR,
                data = data,
                message = msg
            )
        }
    }
}
