package com.lpirro.moovie.data.network.response

open class BaseApiResponse<T: Any> {
    lateinit var results: T
}