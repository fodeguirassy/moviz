package com.example.guirassy.moviz.model

import android.graphics.drawable.Drawable
import java.io.Serializable

/**
 * Created by guirassy on 10/08/2017.
 */
data class Movie(var show_title: String, var release_year : String,
                 var rating : String, var category : String,
                 var show_cast : String, var director : String,
                 var summary : String, var poster : String,
                 var runtime : String, var posterDrawable : Drawable) : Serializable

