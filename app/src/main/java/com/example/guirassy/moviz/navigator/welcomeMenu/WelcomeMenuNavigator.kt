package com.example.guirassy.moviz.navigator.welcomeMenu

import android.content.Context
import android.support.v4.app.FragmentManager
import com.ekino.mvp.FragmentNavigator
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuFragment
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuPresenter

/**
 * Created by guirassy on 16/08/2017.
 */
class WelcomeMenuNavigator(context : Context, fragmentManager : FragmentManager, containerViewId : Int)
    : FragmentNavigator(context,fragmentManager,containerViewId), IWelcomeMenuNavigator
{

    override fun displayMenu() {
        val welcomeMenuFragment = WelcomeMenuFragment()
        val welcomeMenuPresenter = WelcomeMenuPresenter(welcomeMenuFragment, this as Navigator)

        var tarantino = Director("Quentin Tarantino", R.drawable.tarantino_bis)
        var scorsese = Director ("Martin Scorsese", R.drawable.scorsese)
        var directors = listOf(tarantino,scorsese)
        welcomeMenuFragment.presenter = welcomeMenuPresenter

        welcomeMenuFragment.setWelcomeMenu(directors)

        displayAndSetRootFragment(welcomeMenuFragment)
    }

    override fun displayOrRestoreScreenOnActivityCreate() {
        println("")
    }

    override fun displayMovieList(directorName: String) {
        println("IN METHOD DISPLAY MOVIE LIST")
    }

}