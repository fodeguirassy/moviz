package com.example.guirassy.moviz.platform

import android.os.Bundle
import com.example.guirassy.moviz.ui.login.LoginScreenPresenter
import com.facebook.*
import com.facebook.login.LoginResult

class GraphService (var presenter : LoginScreenPresenter): FacebookCallback<LoginResult> {

    override fun onCancel() {
        println("FB connect cancelled")
    }

    override fun onSuccess(result: LoginResult?) {
        var requestParameters = Bundle()
        requestParameters.putString("fields","id,name,link,email,picture,birthday,location,friends,gender,albums")

        var graphBatchRequest = GraphRequestBatch(

                GraphRequest(AccessToken.getCurrentAccessToken(),
                        Profile.getCurrentProfile().id,
                        requestParameters,
                        HttpMethod.GET,
                        GraphRequest.Callback {
                            response: GraphResponse? ->
                            println("GRAPH RESPONSE : ${response.toString()}")
                                val graphObject = response?.jsonObject
                                presenter.user.id = graphObject?.getString("id")
                                presenter.user.name = graphObject?.getString("name")
                                presenter.user.email = graphObject?.getString("email")
                                presenter.user.link = graphObject?.getString("link")
                                presenter.user.birthday = graphObject?.getString("birthday")
                                presenter.user.gender = graphObject?.getString("gender")
                                presenter.user.pictureUrl = graphObject?.getJSONObject("picture")?.getJSONObject("data")
                                        ?.getString("url")
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
    override fun onError(error: FacebookException?) {
        println(error.toString())
    }


}