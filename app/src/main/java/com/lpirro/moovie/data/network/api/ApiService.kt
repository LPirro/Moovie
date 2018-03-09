package com.lpirro.moovie.data.network.api

import android.os.Build
import com.lpirro.moovie.BuildConfig
import com.lpirro.moovie.data.network.response.MoviesResponse
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("discover/movie")
    fun getMovies(@Query("api_key") apiKey: String = BuildConfig.API_KEY,
                  @Query("language") language: String = "en-US",
                  @Query("sort_by") sortBy: String = "popularity.desc"): Single<MoviesResponse>
}
