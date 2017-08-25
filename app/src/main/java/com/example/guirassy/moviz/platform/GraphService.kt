package com.example.guirassy.moviz.platform

import android.os.Bundle
import com.example.guirassy.moviz.platform.api.GraphApi
import com.example.guirassy.moviz.ui.login.LoginScreenPresenter
import com.facebook.*
import com.facebook.login.LoginResult
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GraphService (var presenter : LoginScreenPresenter): FacebookCallback<LoginResult> {

    lateinit var graphApi : GraphApi

    init {
        val okHttpClient = buildGiphyHttpClient()
        val retrofit = buildGraphRetrofit(okHttpClient)
        graphApi = retrofit.create(GraphApi::class.java)
    }

    /*
    fun getFBUserInfos(query : String) {

        graphApi.getFBUser(query)
                .enqueue(object : GraphResponse(), Callback<GraphResponse> {

                    override fun onResponse(call: Call<GraphResponse>?, response: Response<GraphResponse>?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onFailure(call: Call<GraphResponse>?, t: Throwable?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                })

    }
    */

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