package com.example.svomnipro.tools

import retrofit2.HttpException

object Utils {
    fun getException(throwable: Throwable): String {
        throwable.printStackTrace()
        return when (throwable) {
            is HttpException -> throwable.message
                ?: "An unexpected error occurred service no response"

            is Exception -> throwable.message
                ?: "Couldn't reach server. Check your internet connection."

            else -> throwable.message ?: "An unexpected error occurred"
        }
    }
}