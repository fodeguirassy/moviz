package com.example.guirassy.moviz.ui.welcomeMenu

import android.view.View
import com.airbnb.epoxy.OnModelClickListener
import com.airbnb.epoxy.TypedEpoxyController
import com.example.guirassy.moviz.model.Director

class WelcomeMenuController(private var fragment : WelcomeMenuFragment): TypedEpoxyController<List<Director>>(), OnModelClickListener<WelcomeMenuItem, View>{

    override fun onClick(model: WelcomeMenuItem, parentView: View?, clickedView: View?, position: Int) {
        var director = this.adapter.getModelAtPosition(position) as Director
        fragment.presenter.onDirectorSelected(director)
    }

    override fun buildModels(directors: List<Director>) {
        directors.forEach {
            WelcomeMenuItem(it)
                    .id(it.name)
                    .addTo(this)
        }
    }

}