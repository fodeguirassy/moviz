package com.example.guirassy.moviz.ui.userProfile

import com.ekino.mvp.BasePresenter
import com.ekino.mvp.BaseView
import com.example.guirassy.moviz.model.Movie

interface UserProfileScreenContract {
    interface View : BaseView<Presenter> {
        fun setUserMoviesList(movies : List<Movie>)
    }
    interface Presenter : BasePresenter
}