package com.ekino.ekinodemo

import android.content.Context
import android.support.v4.app.FragmentManager
import com.ekino.mvp.FragmentNavigator
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.platform.NetflixRService
import com.example.guirassy.moviz.ui.login.LoginScreenFragment
import com.example.guirassy.moviz.ui.login.LoginScreenPresenter
import com.example.guirassy.moviz.ui.movieDetails.MovieDetailsScreenFragment
import com.example.guirassy.moviz.ui.movieDetails.MovieDetailsScreenPresenter
import com.example.guirassy.moviz.ui.movieList.MovieListFragment
import com.example.guirassy.moviz.ui.movieList.MovieListPresenter
import com.facebook.CallbackManager


class AppFragmentNavigator(context: Context, fragmentManager: FragmentManager, containerViewId: Int) :
        FragmentNavigator(context, fragmentManager, containerViewId), Navigator {

    private val netflixRService = NetflixRService()
    lateinit private var movieListFragment: MovieListFragment

    override fun displayOrRestoreScreenOnActivityCreate() {
        // displayMovieListScreen()
    }

    override fun displayMovieListScreen(director: Director) {
        movieListFragment = MovieListFragment.newInstance(director)
        val presenter = MovieListPresenter(movieListFragment, this, netflixRService, director.name)
        movieListFragment.presenter = presenter
        displayAndSetRootFragment(movieListFragment)
        addToBackStackWithFadeAnimation(movieListFragment)
    }

    override fun displayMovieDetailsScreen(movie: Movie) {
        val movieDetailsFragment = MovieDetailsScreenFragment.newInstance(movie)
        val presenter = MovieDetailsScreenPresenter(movieDetailsFragment, this)
        movieDetailsFragment.presenter = presenter
        addToBackStackWithFadeAnimation(movieDetailsFragment)
    }

    override fun displayPreviousScreen() {
        if(fragmentManager.backStackEntryCount > 0){
            fragmentManager.popBackStack()
        }
    }

    override fun displayLoginScreen() {
        val loginFragment = LoginScreenFragment()
        val loginScreenPresenter = LoginScreenPresenter(loginFragment, this)
        loginFragment.presenter = loginScreenPresenter
        addToBackStackWithFadeAnimation(loginFragment)
    }

}
