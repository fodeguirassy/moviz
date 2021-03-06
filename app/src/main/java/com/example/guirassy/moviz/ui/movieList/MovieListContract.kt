package com.example.guirassy.moviz.ui.movieList

import com.ekino.mvp.BasePresenter
import com.ekino.mvp.BaseView
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.Movie

interface MovieListContract {
    interface View : BaseView<Presenter> {
        fun setMoviesList(customMovies: List<Movie>)
    }

    interface Presenter : BasePresenter {
        fun moviePressed(movie: Movie)
        fun onDirectorSelected(director : Director)
    }
}