package com.example.guirassy.moviz.ui.welcomeMenu

import com.airbnb.epoxy.TypedEpoxyController
import com.example.guirassy.moviz.model.Director

class WelcomeMenuController : TypedEpoxyController<List<Director>>() {

    override fun buildModels(directors: List<Director>) {
        directors.forEach {
            WelcomeMenuItem(it)
                    .id(it.name)
                    .addTo(this)
        }
    }
}