package com.example.guirassy.moviz.ui.userProfile

import com.example.guirassy.moviz.Navigator
import com.ekino.mvp.MvpPresenter

class UserProfileScreenPresenter(view: UserProfileScreenContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, UserProfileScreenContract.View>(view, navigator),
        UserProfileScreenContract.Presenter {



}