package com.example.guirassy.moviz.platform

import com.example.guirassy.moviz.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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

fun buildGraphRetrofit(httpClient: OkHttpClient) : Retrofit {

    return Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://graph.facebook.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}