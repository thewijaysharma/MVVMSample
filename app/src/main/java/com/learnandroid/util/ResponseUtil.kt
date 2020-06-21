package com.learnandroid.util

import com.learnandroid.base.Status

data class Response<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        const val API_ERROR = "Something went wrong"
        const val NO_INTERNET = "Please check your internet connection and try again"
        const val TIMEOUT = "Request timed out. Please try again"

        fun <T> success(data: T?): Response<T> {
            return Response(Status.SUCCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Response<T> {
            return Response(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Response<T> {
            return Response(Status.LOADING, data, null)
        }

    }

}