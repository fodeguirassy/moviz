package com.example.guirassy.moviz.platform.api

import com.facebook.GraphResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GraphApi {
    @GET("/v2.10/me?")
    fun getFBUser(@Query("fields") fields: String,
                  @Query("access_token") accessToken: String
    ) : Call<GraphResponse>
}