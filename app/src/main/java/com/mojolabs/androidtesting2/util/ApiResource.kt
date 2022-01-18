package com.mojolabs.androidtesting2.util

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                status = Status.LOADING,
                data = data,
                message = null
            )
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(
                status = Status.SUCCESS,
                data = data,
                message = null
            )
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(
                status = Status.ERROR,
                data = data,
                message = msg
            )
        }
    }
}
