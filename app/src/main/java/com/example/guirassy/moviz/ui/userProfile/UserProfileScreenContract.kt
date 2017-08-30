package com.example.guirassy.moviz.ui.userProfile

import com.ekino.mvp.BasePresenter
import com.ekino.mvp.BaseView

interface UserProfileScreenContract {
    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}