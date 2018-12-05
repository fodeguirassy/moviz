package com.example.guirassy.moviz.ui.userProfile

import com.example.guirassy.moviz.Navigator
import com.ekino.mvp.MvpPresenter
import com.example.guirassy.moviz.perisistence.Preferences

class UserProfileScreenPresenter(view: UserProfileScreenContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, UserProfileScreenContract.View>(view, navigator),
        UserProfileScreenContract.Presenter {


    override fun resume() {
        super.resume()
        val user = Preferences.retrieveUserFromPrefs()
        view.setUserMoviesList(user!!.savedMovies)
    }
}