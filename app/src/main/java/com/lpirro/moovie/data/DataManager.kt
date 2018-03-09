package com.lpirro.moovie.data

import com.lpirro.moovie.data.network.api.ApiService
import com.lpirro.moovie.data.network.api.ApiServiceFactory
import com.lpirro.moovie.data.network.response.MoviesResponse
import io.reactivex.Single

object DataManager {

    private var sApiService: ApiService = ApiServiceFactory.makeApiService()

    /**
     *  Get movies from the network
     */
    fun getMovies(): Single<MoviesResponse> {
        return sApiService.getMovies()
    }

}
