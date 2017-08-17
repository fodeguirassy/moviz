package com.example.guirassy.moviz.ui.movieList

import com.ekino.mvp.MvpPresenter
import com.example.guirassy.moviz.MainActivity
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.platform.NetflixRApiCallback
import com.example.guirassy.moviz.platform.NetflixRService

class MovieListPresenter(view: MovieListContract.View, navigator: Navigator, private val netflixRService : NetflixRService, private val directorName : String) :
        MvpPresenter<Navigator, MovieListContract.View>(view, navigator),
        MovieListContract.Presenter {

    override fun resume() {
        super.resume()
        netflixRService.search(directorName, object : NetflixRApiCallback{
            override fun onSuccess(movies: List<Movie>) {
                view.setMoviesList(movies)
            }
            override fun onError(t: Throwable?) {}
        })
    }

    override fun moviePressed(movie: Movie) {
        navigator.displayMovieDetailsScreen(movie)
    }

    override fun onDirectorSelected(director: Director) {
        navigator.displayMovieList(director.name)
    }
}
