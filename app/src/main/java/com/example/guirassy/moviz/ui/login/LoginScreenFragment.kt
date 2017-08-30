package com.example.guirassy.moviz.ui.login


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.ekino.mvp.MvpFragment
import com.example.guirassy.moviz.MainActivity
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.platform.GraphService
import com.facebook.login.LoginManager
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_login_fragment.*


class LoginScreenFragment : MvpFragment<LoginScreenContract.Presenter>(),
        LoginScreenContract.View {

    override val defaultLayout: Int = R.layout.fragment_login_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      // Blurry.with(view.context).radius(250).sampling(10).onto(this.view?.parent as ViewGroup)

        var actionBar = (activity as AppCompatActivity).supportActionBar
        (activity as MainActivity).toggle.isDrawerIndicatorEnabled = false
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "Login"

        facebook_connect.setOnClickListener {
           presenter.onFacebookLogin()
       }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        (presenter as LoginScreenPresenter).callbackManager.onActivityResult(requestCode,resultCode,data)
    }
}