package com.ekino.ekinodemo

import android.content.Context
import android.support.v4.app.FragmentManager
import com.ekino.mvp.FragmentNavigator
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.platform.NetflixRService
import com.example.guirassy.moviz.ui.MovieListFragment
import com.example.guirassy.moviz.ui.MovieListPresenter
import com.example.guirassy.moviz.ui.movieDetails.MovieDetailsScreenFragment
import com.example.guirassy.moviz.ui.movieDetails.MovieDetailsScreenPresenter
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuFragment
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuPresenter


class AppFragmentNavigator(context: Context, fragmentManager: FragmentManager, containerViewId: Int) :
        FragmentNavigator(context, fragmentManager, containerViewId), Navigator{


    private val netflixRservice = NetflixRService()

    /*
     * FragmentNavigator class overrides
     * */

    override val TAG = "ekino-demo.navigator"

    override fun displayDirectors() {
        var tarantino = Director("Quentin Tarantino", R.drawable.tarantino_bis)
        var scorsese = Director ("Martin Scorsese", R.drawable.scorsese)
        var directors = listOf(tarantino,scorsese)

        val welcomeMenuFragment = WelcomeMenuFragment()
        val welcomeMenuPresenter = WelcomeMenuPresenter(welcomeMenuFragment, this)
        welcomeMenuFragment.presenter = welcomeMenuPresenter
        welcomeMenuFragment.setWelcomeMenu(directors)
        displayAndSetRootFragment(welcomeMenuFragment)

    }

    override fun displayOrRestoreScreenOnActivityCreate() {
        // displayMovieList()
    }

    /*
     * Navigator interface overrides
     * */
    override fun displayMovieList(directorName : String) {
        val fragment = MovieListFragment()
        val presenter = MovieListPresenter(fragment, this, netflixRservice, directorName)
        fragment.presenter = presenter
        displayAndSetRootFragment(fragment)
        addToBackStackWithFadeAnimation(fragment)
    }

    override fun displayMovieDetailsScreen(movie: Movie) {
        val movieDetailsFragment = MovieDetailsScreenFragment.newInstance(movie)
        val presenter = MovieDetailsScreenPresenter(movieDetailsFragment, this)
        movieDetailsFragment.presenter = presenter
        displayAndSetRootFragment(movieDetailsFragment)
        addToBackStackWithFadeAnimation(movieDetailsFragment)
    }

    override fun displayPreviousFragment() {
        fragmentManager.popBackStack()
        //this.onBackPressed()
    }
}
