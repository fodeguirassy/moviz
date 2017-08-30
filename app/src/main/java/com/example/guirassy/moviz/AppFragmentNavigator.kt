package com.ekino.ekinodemo

import android.content.Context
import android.support.v4.app.FragmentManager
import com.ekino.mvp.FragmentNavigator
import com.example.guirassy.moviz.MainActivity
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.model.User
import com.example.guirassy.moviz.perisistence.Preferences
import com.example.guirassy.moviz.platform.NetflixRService
import com.example.guirassy.moviz.ui.login.LoginScreenFragment
import com.example.guirassy.moviz.ui.login.LoginScreenPresenter
import com.example.guirassy.moviz.ui.movieDetails.MovieDetailsScreenFragment
import com.example.guirassy.moviz.ui.movieDetails.MovieDetailsScreenPresenter
import com.example.guirassy.moviz.ui.movieList.MovieListFragment
import com.example.guirassy.moviz.ui.movieList.MovieListPresenter
import com.example.guirassy.moviz.ui.userProfile.UserProfileScreenFragment
import com.example.guirassy.moviz.ui.userProfile.UserProfileScreenPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.app_bar_main.*


class AppFragmentNavigator(context: Context, fragmentManager: FragmentManager, containerViewId: Int) :
        FragmentNavigator(context, fragmentManager, containerViewId), Navigator {

    private val netflixRService = NetflixRService()
    lateinit private var movieListFragment: MovieListFragment
    lateinit private var loginScreenPresenter : LoginScreenPresenter

    lateinit private var movieDetailsPresenter : MovieDetailsScreenPresenter

    private val PREFS = "PREFS_USER"


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
        movieDetailsPresenter = MovieDetailsScreenPresenter(movieDetailsFragment, this)
        movieDetailsFragment.presenter = movieDetailsPresenter
        addToBackStackWithFadeAnimation(movieDetailsFragment)
    }

    override fun displayPreviousScreen() {
        if(fragmentManager.backStackEntryCount > 0){
            fragmentManager.popBackStack()
        }
    }

    override fun displayLoginScreen() {
        val loginFragment = LoginScreenFragment()
        loginScreenPresenter = LoginScreenPresenter(loginFragment, this)
        loginFragment.presenter = loginScreenPresenter
        addToBackStackWithFadeAnimation(loginFragment)
    }

    override fun displayUserProfileScreen(user: User) {
        var userProfileFragment = UserProfileScreenFragment.newInstance(user)
        var userProfilePresenter = UserProfileScreenPresenter(userProfileFragment,this)
        userProfileFragment.presenter = userProfilePresenter
        addToBackStackWithFadeAnimation(userProfileFragment)
    }

    override fun getUserFromPrefs() : User?{ return Preferences.retrieveUserFromPrefs(context) }

    override fun saveUserInPrefs(user : User){ Preferences.saveUserInPrefs(context,user) }

    override fun switchFabIcon() {
        var fab = (context as MainActivity).fab
        val accountDrawable = context.resources.getDrawable(R.drawable.account,null)
        val addDrawable = context.resources.getDrawable(R.drawable.movie_add,null)
        if (fab.drawable == accountDrawable){
            fab.setImageDrawable(addDrawable)

        }
    }

}
