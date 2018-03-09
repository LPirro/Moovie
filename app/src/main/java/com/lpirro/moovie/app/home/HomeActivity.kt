package com.lpirro.moovie.app.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.lpirro.moovie.R
import com.lpirro.moovie.app.home.adapter.MoviesAdapter
import com.lpirro.moovie.data.DataManager
import com.lpirro.moovie.data.models.Movie
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), HomeContract.HomeView {

    lateinit var presenter: HomePresenter
    var adapter: MoviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = HomePresenter(DataManager)
        adapter = MoviesAdapter()

        rvMovies.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rvMovies.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        presenter.getMovies()
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
        presenter.unsubscribe()
    }

    override fun showLoadingView() {
    }

    override fun hideLoadingView() {

    }

    override fun showError(errorMessage: String) {
        Log.e("ERROR", errorMessage)
    }

    override fun onMovieResult(movies: List<Movie>) {
        adapter.setMovies(movies)
    }
}
