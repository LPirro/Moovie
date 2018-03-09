package com.lpirro.moovie.mvp

interface View {

    fun showLoadingView()

    fun hideLoadingView()

    fun showError(errorMessage: String)
}
