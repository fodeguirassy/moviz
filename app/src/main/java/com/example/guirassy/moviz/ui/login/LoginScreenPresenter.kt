package com.example.guirassy.moviz.ui.login

import com.ekino.mvp.MvpPresenter
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.model.User
import com.example.guirassy.moviz.platform.GraphService
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import retrofacebook.Facebook
import java.util.*


class LoginScreenPresenter(view: LoginScreenContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, LoginScreenContract.View>(view, navigator),
        LoginScreenContract.Presenter{

    lateinit var user : User
    lateinit var facebook : Facebook

     var callbackManager = CallbackManager.Factory.create()

    override fun resume() {
        super.resume()
        //facebook = Facebook.create((view as LoginScreenFragment).activity)
    }

    override fun onFacebookLogin() {
        user = User()
        LoginManager.getInstance().logInWithReadPermissions(view as LoginScreenFragment, Arrays.asList("public_profile","email","user_friends","user_location","user_birthday"))
        LoginManager.getInstance().registerCallback(callbackManager, GraphService(this))
    }
}