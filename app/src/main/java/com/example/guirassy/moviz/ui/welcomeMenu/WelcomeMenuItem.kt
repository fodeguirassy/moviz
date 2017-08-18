package com.example.guirassy.moviz.ui.welcomeMenu

import android.support.constraint.ConstraintLayout
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyModel
import com.bumptech.glide.Glide
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Director


class WelcomeMenuItem(private val director: Director, private val fragment: WelcomeMenuFragment) : EpoxyModel<ConstraintLayout>() {

    override fun getDefaultLayout(): Int {
        return R.layout.menu_list_row_item
    }

    override fun bind(view: ConstraintLayout) {
        val imageView = view.findViewById<ImageView>(R.id.list_director_picture_iv)
        val directorNameTv = view.findViewById<TextView>(R.id.list_director_name_tv)

        directorNameTv.text = director.name
        Glide.with(view.context)
                .load(director.pictureResId)
                .into(imageView)

        view.setOnClickListener {
            fragment.onDirectorSelected(director)
        }
    }
}