package com.example.guirassy.moviz.model

/**
 * Created by guirassy on 10/08/2017.
 */

data class Director(val name : String, val pictureResId : Int) {
    var movies = mutableListOf<Movie>()

}