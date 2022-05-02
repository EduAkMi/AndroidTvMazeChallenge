package com.jobsitychallenge.api

sealed class ResponseApi {
    class Success(var data: Any?) : ResponseApi()
    class Error(val message: String) : ResponseApi()
}