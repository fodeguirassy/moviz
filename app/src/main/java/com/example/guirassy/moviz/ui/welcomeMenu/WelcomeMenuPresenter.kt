package com.example.guirassy.moviz.ui.welcomeMenu

import com.ekino.mvp.MvpPresenter
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Director

class WelcomeMenuPresenter(view: WelcomeMenuContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, WelcomeMenuContract.View>(view, navigator),
        WelcomeMenuContract.Presenter {

    override fun resume() {
        super.resume()

        var tarantino = Director("Quentin Tarantino", R.drawable.tarantino_bis)
        var scorsese = Director("Martin Scorsese", R.drawable.scorsese)
        var directors = listOf(tarantino, scorsese,
                Director("Spike Lee", R.drawable.lee),
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

        view.setWelcomeMenu(directors)
    }

    override fun onDirectorSelected(director: Director) {
        navigator.displayMovieListScreen(director)
    }
}