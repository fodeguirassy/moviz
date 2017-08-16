package com.example.guirassy.moviz.platform

import com.example.guirassy.moviz.model.Movie

/**
 * Created by guirassy on 14/08/2017.
 */
interface NetflixRApiCallback {
    fun onSuccess(movies : List<Movie>)
    fun onError(t: Throwable?)
}