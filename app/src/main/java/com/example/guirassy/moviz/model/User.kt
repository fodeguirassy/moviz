package com.example.guirassy.moviz.model

import java.io.Serializable


class User : Serializable {

    var name: String? = null
        get() = field
        set(value){field =value}

     var id : String? = null
        get() = field
        set(value){field = value}

     var link : String? = null
        get() = field
        set(value){field = value}

     var email : String ? = null
        get() = field
        set(value){field = value}

     var pictureUrl : String? = null
        get() = field
        set(value){field = value}

     var birthday : String ? = null
        get() = field
        set(value) {field = value}

     var gender : String ? = null
        get() = field
        set(value){field = value}

     var savedMovies : MutableList<Movie> = mutableListOf()
        get() = field
        set(value) {field = value}

}
