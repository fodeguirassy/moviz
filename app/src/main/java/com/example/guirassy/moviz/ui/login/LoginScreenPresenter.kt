package com.example.guirassy.moviz.ui.login

import android.os.Bundle
import android.util.Log
import com.example.guirassy.moviz.Navigator
import com.ekino.mvp.MvpPresenter
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import org.json.JSONObject
import java.util.*



class IFacebookConnect : FacebookCallback<LoginResult> {

    override fun onCancel() {
        println("FB connect cancelled")
    }

    override fun onSuccess(result: LoginResult?) {
        println("\tACCESSTOKEN : ${result?.accessToken?.token}")
        println("\tUSER_ID ${Profile.getCurrentProfile().id}")

        var requestParameters = Bundle()
        requestParameters.putString("fields","id,name,last_name,link,email,picture")

        GraphRequest(AccessToken.getCurrentAccessToken(),
                Profile.getCurrentProfile().id,
                requestParameters,
                HttpMethod.GET,
                GraphRequest.Callback {
                    response: GraphResponse? ->
                    println("GRAPH RESPONSE : ${response.toString()}")
                }).executeAsync()
    }
    override fun onError(error: FacebookException?) {
        println(error.toString())
    }
}

class LoginScreenPresenter(view: LoginScreenContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, LoginScreenContract.View>(view, navigator),
        LoginScreenContract.Presenter{

    lateinit var callbackManager : CallbackManager

    override fun resume() {
        super.resume()
        this.callbackManager = CallbackManager.Factory.create()
    }

    override fun onFacebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(view as LoginScreenFragment, Arrays.asList("public_profile","email"))
        LoginManager.getInstance().registerCallback(callbackManager, IFacebookConnect())
    }

   fun onSuccess(result: LoginResult?) {
        var requestParameters = Bundle()
        requestParameters.putString("fields","id,name,last_name,link,email,picture")
        var graphRequest = GraphRequest.newMeRequest(
                result?.accessToken,
                { `object`: JSONObject?, response: GraphResponse? ->
                    Log.i("FBLogin", `object`?.getString("id"))
                }

        )
        graphRequest.parameters = requestParameters
        graphRequest.executeAsync()
    }
}