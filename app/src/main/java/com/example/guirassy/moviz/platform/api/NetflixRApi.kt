package com.example.guirassy.moviz.platform.api

import com.example.guirassy.moviz.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

/**
 * Created by guirassy on 14/08/2017.
 */
interface NetflixRApi
{
    @GET("/api/api.php")
    fun getDirectorMovies(@Query("director", encoded = true) query: String): Call<List<Movie>>
}