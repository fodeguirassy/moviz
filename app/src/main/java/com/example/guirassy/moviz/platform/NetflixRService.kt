package com.example.guirassy.moviz.platform

import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.platform.api.NetflixRApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder

class NetflixRService {

    private var netflixRApi : NetflixRApi

        init {
            val okHttpClient = buildGiphyHttpClient()
            val retrofit = buildNetflixRRetrofit(okHttpClient)
            netflixRApi = retrofit.create(NetflixRApi::class.java)

        }

        fun search (query : String, apiCallback : NetflixRApiCallback) {
            var encodedUrl = URLEncoder.encode(query, "UTF-8")
                 netflixRApi.getDirectorMovies(encodedUrl)
                    .enqueue(object : Callback<List<Movie>>{
                        override fun onFailure(call: Call<List<Movie>>?, t: Throwable?) {
                            apiCallback.onError(t)
                        }
                        override fun onResponse(call: Call<List<Movie>>?, response: Response<List<Movie>>?) {
                            val responseBody = response?.body().orEmpty()
                            apiCallback.onSuccess(responseBody)
                            //response?.body().toString()
                        }
                    })

        }
}