package com.lpirro.moovie.app.home

import com.lpirro.moovie.data.models.Movie
import com.lpirro.moovie.mvp.View

interface HomeContract{

     interface ViewActions {
        fun getMovies()
     }

    interface HomeView: View {
        fun onMovieResult(movies: List<Movie>)
    }
}