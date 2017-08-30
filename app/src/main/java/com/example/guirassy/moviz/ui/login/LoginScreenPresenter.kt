package com.example.guirassy.moviz.ui.login

import android.os.Bundle
import com.ekino.mvp.MvpPresenter
import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.model.User
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.util.*


class LoginScreenPresenter(view: LoginScreenContract.View, navigator: Navigator) :
        MvpPresenter<Navigator, LoginScreenContract.View>(view, navigator),
        LoginScreenContract.Presenter{

    lateinit var user : User
     var callbackManager = CallbackManager.Factory.create()!!

    override fun resume() {
        super.resume()
    }

    override fun onFacebookLogin() {
        user = User()
        val loginManager = LoginManager.getInstance()
        loginManager.logInWithReadPermissions(view as LoginScreenFragment, Arrays.asList("public_profile","email","user_friends","user_location","user_birthday,user_likes"))
        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{

            override fun onCancel() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onSuccess(result: LoginResult?) {
                val fields = Bundle()
                fields.putString("fields","name")
                if(result != null){
                    var requestParameters = Bundle()
                    requestParameters.putString("fields","id,name,link,email,picture,birthday,location,friends,gender,albums")
                    val graphBatchRequest = GraphRequestBatch(
                            GraphRequest(AccessToken.getCurrentAccessToken(),
                                    Profile.getCurrentProfile().id,
                                    requestParameters,
                                    HttpMethod.GET,
                                    GraphRequest.Callback {
                                        response: GraphResponse? ->
                                        println("GRAPH RESPONSE : ${response.toString()}")
                                        val graphObject = response?.jsonObject

                                        user.id = graphObject?.getString("id")
                                        user.name = graphObject?.getString("name")
                                        user.email = graphObject?.getString("email")
                                        user.link = graphObject?.getString("link")
                                        user.birthday = graphObject?.getString("birthday")
                                        user.gender = graphObject?.getString("gender")
                                        user.pictureUrl = graphObject?.getJSONObject("picture")?.getJSONObject("data")
                                                ?.getString("url")

                                        navigator.saveUserInPrefs(user)
                                        navigator.displayUserProfileScreen(user)

                                    }),
                            GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(),
                                    {
                                        objects, response ->
                                        println("FRIENDS REQUEST  objects : $objects")
                                        println("FRIENDS REQUESt response : $response")
                                    })
                    )
                    graphBatchRequest.executeAsync()
                }
            }
            override fun onError(error: FacebookException?) {
                println("LoginScreenPresenter onFacebookLogin onError $error")
            }
        })
    }

}