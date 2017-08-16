package com.example.guirassy.moviz.ui.welcomeMenu

import com.example.guirassy.moviz.Navigator
import com.ekino.mvp.MvpPresenter
import com.example.guirassy.moviz.model.Director

class WelcomeMenuPresenter(view: WelcomeMenuContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, WelcomeMenuContract.View>(view, navigator),
        WelcomeMenuContract.Presenter {

    override fun onDirectorSelected(director: Director) {
        println("${director.name}")
    }

}