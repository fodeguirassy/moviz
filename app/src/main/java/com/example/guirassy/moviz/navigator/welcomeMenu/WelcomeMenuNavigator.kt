package com.example.guirassy.moviz.navigator.welcomeMenu

import android.content.Context
import android.support.v4.app.FragmentManager
import com.ekino.mvp.FragmentNavigator
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuFragment
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuPresenter

/**
 * Created by guirassy on 16/08/2017.
 */
class WelcomeMenuNavigator(context : Context, fragmentManager : FragmentManager, containerViewId : Int)
    : FragmentNavigator(context,fragmentManager,containerViewId), IWelcomeMenuNavigator, Navigator
{

    override fun displayMenu() {
        val welcomeMenuFragment = WelcomeMenuFragment()
        val welcomeMenuPresenter = WelcomeMenuPresenter(welcomeMenuFragment, this)
        welcomeMenuFragment.presenter = welcomeMenuPresenter
        displayAndSetRootFragment(welcomeMenuFragment)
    }

    override fun displayOrRestoreScreenOnActivityCreate() {
        println("")
    }

    override fun displayMovieList(directorName: String) {
        println("IN METHOD DISPLAY MOVIE LIST")
    }

    override fun displayMovieDetailsScreen(movie: Movie) {
        println("IN METHOD DISPLAY MOVIE DEATAILS SCREEN")
    }

    override fun displayPreviousFragment() {
        println("IN METHOD DISPLAY PREVIOUS FRAGMENT")    }

    override fun displayDirectors() {
        println("IN METHOD DISPLAY DIRECTORS")
    }

}