package com.example.guirassy.moviz.ui.movieDetails

import com.ekino.mvp.BasePresenter
import com.ekino.mvp.BaseView

interface MovieDetailsScreenContract {
    interface View : BaseView<Presenter>
    interface Presenter : BasePresenter {
        fun displayLoginScreen()
    }
}