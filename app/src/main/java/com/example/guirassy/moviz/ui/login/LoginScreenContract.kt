package com.example.guirassy.moviz.ui.login

import com.ekino.mvp.BasePresenter
import com.ekino.mvp.BaseView

interface LoginScreenContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {
    }

}