package com.example.guirassy.moviz.ui.welcomeMenu

import com.ekino.mvp.MvpPresenter
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.platform.NetflixRService
import com.example.guirassy.moviz.ui.movieList.MovieListFragment
import com.example.guirassy.moviz.ui.movieList.MovieListPresenter

class WelcomeMenuPresenter(view: WelcomeMenuContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, WelcomeMenuContract.View>(view, navigator),
        WelcomeMenuContract.Presenter {


    private val movieListFragment = MovieListFragment()
    private var movieListPresenter  =  MovieListPresenter(movieListFragment,navigator, NetflixRService(), "")

    override fun resume() {
        super.resume()
    }

    override fun onDirectorSelected(director: Director) {
        navigator.displayMovieList(director.name)
        //movieListFragment.presenter.onDirectorSelected(director)
        //println("${director.name}")
    }

}