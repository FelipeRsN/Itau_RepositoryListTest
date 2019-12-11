package com.felipersn.itaurepositorylist.common.utils

//Used to set observer response state.
class Resource<T> private constructor(val status: Status, val data: T? = null, val error: String? = null) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(error: String? = null): Resource<T> {
            return Resource(Status.ERROR, error = error)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data)
        }
    }
}