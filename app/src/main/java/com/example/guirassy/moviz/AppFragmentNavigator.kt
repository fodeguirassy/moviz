package com.ekino.ekinodemo

import android.content.Context
import android.support.v4.app.FragmentManager
import com.ekino.mvp.FragmentNavigator
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.platform.NetflixRService
import com.example.guirassy.moviz.ui.movieList.MovieListFragment
import com.example.guirassy.moviz.ui.movieList.MovieListPresenter
import com.example.guirassy.moviz.ui.movieDetails.MovieDetailsScreenFragment
import com.example.guirassy.moviz.ui.movieDetails.MovieDetailsScreenPresenter
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuFragment
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuPresenter


class AppFragmentNavigator(context: Context, fragmentManager: FragmentManager, containerViewId: Int) :
        FragmentNavigator(context, fragmentManager, containerViewId), Navigator {

    private val netflixRService = NetflixRService()
    lateinit private var movieListFragment: MovieListFragment

    override fun displayDirectors() {
        var tarantino = Director("Quentin Tarantino", R.drawable.tarantino_bis)
        var scorsese = Director("Martin Scorsese", R.drawable.scorsese)
        var directors = listOf(tarantino, scorsese,
                Director("Spike Lee",R.drawable.lee),
                Director("Clint Eastwood", R.drawable.eastwood),
                Director("Ridley Scott", R.drawable.scott),
                Director("Tim Burton", R.drawable.burton),
                Director("James Burton", R.drawable.cameron),
                Director("Christopher Nolan", R.drawable.nolan),
                Director("David Lynch", R.drawable.lynch),
                Director("Woody Allen", R.drawable.woodyallen),
                Director("Steven Spielberg", R.drawable.spielberg),
                Director("Alfred Hitcock", R.drawable.hitchcock)
        )

        val welcomeMenuFragment = WelcomeMenuFragment()
        val welcomeMenuPresenter = WelcomeMenuPresenter(welcomeMenuFragment, this)
        welcomeMenuFragment.presenter = welcomeMenuPresenter
        welcomeMenuFragment.setWelcomeMenu(directors)
        fragmentManager.beginTransaction().replace(R.id.menu_container, welcomeMenuFragment).commit()
    }

    override fun displayOrRestoreScreenOnActivityCreate() {
        // displayMovieList()
    }

    override fun displayMovieList(directorName: String) {
        movieListFragment = MovieListFragment()
        val presenter = MovieListPresenter(movieListFragment, this, netflixRService, directorName)
        movieListFragment.presenter = presenter
        displayAndSetRootFragment(movieListFragment)
        // addToBackStackWithFadeAnimation(fragment)
    }

    override fun displayMovieDetailsScreen(movie: Movie) {
        val movieDetailsFragment = MovieDetailsScreenFragment.newInstance(movie)
        val presenter = MovieDetailsScreenPresenter(movieDetailsFragment, this)
        movieDetailsFragment.presenter = presenter
        addToBackStackWithFadeAnimation(movieDetailsFragment)
    }

    override fun displayPreviousFragment() {
        fragmentManager.popBackStack()
        //this.onBackPressed()
    }
}
