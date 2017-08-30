package com.example.guirassy.moviz.ui.movieDetails

import com.ekino.mvp.MvpPresenter
import com.example.guirassy.moviz.Navigator

class MovieDetailsScreenPresenter(view: MovieDetailsScreenContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, MovieDetailsScreenContract.View>(view, navigator),
        MovieDetailsScreenContract.Presenter {

    override fun displayLoginScreen() {
        navigator.displayLoginScreen()
    }
}