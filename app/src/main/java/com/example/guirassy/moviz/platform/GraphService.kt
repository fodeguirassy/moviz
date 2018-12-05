package com.example.guirassy.moviz.platform

import com.example.guirassy.moviz.platform.api.GraphApi
import com.facebook.GraphResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GraphService {

    var graphApi : GraphApi

    init {
        val okHttpClient = buildGiphyHttpClient()
        val retrofit = buildGraphRetrofit(okHttpClient)
        graphApi = retrofit.create(GraphApi::class.java)
    }

    fun getFBUserInfo(query: String,fields: String) {
        graphApi.getFBUser(fields, query)
                .enqueue(object :  Callback<GraphResponse> {
                    override fun onFailure(call: Call<GraphResponse>?, t: Throwable?) {
                        println("GraphService getFBUserInfo onFailure ${t.toString()}")
                    }
                    override fun onResponse(call: Call<GraphResponse>?, response: Response<GraphResponse>?) {
                        println("GraphService getFBUserInfo onResponse")
                        println("${response?.body()}")
                    }
                })
    }
}