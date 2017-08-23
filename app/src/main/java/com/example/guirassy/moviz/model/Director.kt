package com.example.guirassy.moviz.model

import java.io.Serializable

/**
 * Created by guirassy on 10/08/2017.
 */

data class Director(val name : String, val pictureResId : Int) : Serializable {
    var movies = mutableListOf<Movie>()

}