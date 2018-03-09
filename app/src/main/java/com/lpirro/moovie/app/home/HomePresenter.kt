package com.lpirro.moovie.app.home

import com.lpirro.moovie.data.DataManager
import com.lpirro.moovie.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter (private val dataManager: DataManager) : BasePresenter<HomeContract.HomeView>(),
        HomeContract.ViewActions {

    private val mCompositeDisposable = CompositeDisposable()

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun getMovies() {
        requestMovies()
    }

    private fun requestMovies(){

        mView?.showLoadingView()

        val disposable =  dataManager.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response -> response.results }
                .doFinally { mView?.hideLoadingView() }
                .subscribe(
                        { movies -> mView?.onMovieResult(movies)},
                        { error: Throwable -> mView?.showError(error.localizedMessage)}
                )

        mCompositeDisposable.add(disposable)
    }
}
