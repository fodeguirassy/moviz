package com.example.guirassy.moviz.ui.login

import com.example.guirassy.moviz.Navigator
import com.ekino.mvp.MvpPresenter

class LoginScreenPresenter(view: LoginScreenContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, LoginScreenContract.View>(view, navigator),
        LoginScreenContract.Presenter {


}