package com.example.guirassy.moviz.ui.welcomeMenu

import com.ekino.mvp.BasePresenter
import com.ekino.mvp.BaseView
import com.example.guirassy.moviz.model.Director

interface WelcomeMenuContract {
    interface View : BaseView<Presenter> {
        fun setWelcomeMenu(directors : List<Director>)
    }
    interface Presenter : BasePresenter {
        fun onDirectorSelected(director : Director)
    }
}