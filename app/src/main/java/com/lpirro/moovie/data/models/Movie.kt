package com.lpirro.moovie.data.models

class Movie (val title: String, private val poster_path: String, val release_date: String) {
    val poster: String get() = "https://image.tmdb.org/t/p/w500$poster_path"
}
