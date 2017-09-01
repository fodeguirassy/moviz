package com.example.guirassy.moviz.model

import android.graphics.drawable.Drawable
import java.io.Serializable

/**
 * Created by guirassy on 10/08/2017.
 */
data class Movie(val show_title: String, val release_year : String,
                 val rating : String, val category : String,
                 val show_cast : String, val director : String,
                 val summary : String, val poster : String,
                 val runtime : String, val posterDrawable : Drawable) : Serializable
