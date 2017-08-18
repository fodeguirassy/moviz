package com.example.guirassy.moviz.ui.login


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.example.guirassy.moviz.R
import com.ekino.mvp.MvpFragment
import jp.wasabeef.blurry.Blurry

class LoginScreenFragment : MvpFragment<LoginScreenContract.Presenter>(),
        LoginScreenContract.View {
    override val defaultLayout: Int = R.layout.fragment_login_fragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       Blurry.with(view.context).radius(250).sampling(10).onto(this.view?.parent as ViewGroup)

    }
}