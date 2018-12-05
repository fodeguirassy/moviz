package com.example.guirassy.moviz.ui.userProfile

import android.support.constraint.ConstraintLayout
import android.widget.LinearLayout
import com.airbnb.epoxy.EpoxyModel
import com.bumptech.glide.Glide
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Movie
import kotlinx.android.synthetic.main.user_movies_item.view.*

class UserMoviesListItem(private val movie:Movie) : EpoxyModel<ConstraintLayout>(){

    override fun getDefaultLayout(): Int {
        return R.layout.user_movies_item
    }

    override fun bind(view: ConstraintLayout) {
        Glide.with(view.context)
                .load(movie.poster)
                .placeholder(R.drawable.app_icon)
                .into(view.user_movies_item_imgView)

        view.user_movies_item_casting.text = movie.show_cast
        view.user_movies_item_description.text = movie.summary
        view.user_movies_item_title.text = movie.show_title
    }
}