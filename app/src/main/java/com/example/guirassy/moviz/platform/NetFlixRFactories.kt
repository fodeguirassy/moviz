package com.example.guirassy.moviz.platform

import com.example.guirassy.moviz.BuildConfig
import com.example.guirassy.moviz.platform.api.NetflixRApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by guirassy on 14/08/2017.
 */


fun buildGiphyHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            })
            .build()
}


fun buildNetflixRRetrofit(httpClient: OkHttpClient) : Retrofit{
    return Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://netflixroulette.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

fun buildNetflixRApi(retrofit: Retrofit) : NetflixRApi {
    return retrofit.create(NetflixRApi::class.java)
}