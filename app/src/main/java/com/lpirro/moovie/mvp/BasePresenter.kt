package com.lpirro.moovie.mvp

abstract class BasePresenter<V: Any>: Presenter {

    protected var mView: V? = null

    fun attachView(view: V) {
        mView = view
    }

    fun detachView() {
        mView = null
    }


}
