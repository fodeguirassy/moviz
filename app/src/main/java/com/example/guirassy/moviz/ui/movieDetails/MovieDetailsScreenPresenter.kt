package com.example.guirassy.moviz.ui.movieDetails

import com.example.guirassy.moviz.Navigator
import com.ekino.mvp.MvpPresenter

class MovieDetailsScreenPresenter(view: MovieDetailsScreenContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, MovieDetailsScreenContract.View>(view, navigator),
        MovieDetailsScreenContract.Presenter {

}