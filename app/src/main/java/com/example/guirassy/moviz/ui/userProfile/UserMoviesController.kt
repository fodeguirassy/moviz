package com.example.guirassy.moviz.ui.userProfile

import com.airbnb.epoxy.TypedEpoxyController
import com.example.guirassy.moviz.model.Movie


class UserMoviesController : TypedEpoxyController<List<Movie>>() {

    override fun buildModels(movies: List<Movie>) {
        movies.forEach {
            UserMoviesListItem(it)
                    .id(it.show_title)
                    //.addIf(this.currentData?.contains(it) != true,this)
                    .addTo(this)
        }
    }
}