package com.example.guirassy.moviz.ui.movieList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.ui.movieList.MovieListFragment
import kotlinx.android.synthetic.main.movies_list_row_item.view.*

/**
 * Created by guirassy on 11/08/2017.
 */
class MovieListAdapter(private var context : MovieListFragment, private var movies : List<Movie>) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val inflater = context.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view : View = inflater.inflate(R.layout.movies_list_row_item, null)

        Glide
                .with(context)
                .load(movies[p0].poster)
                .into(view.movie_list_img_view)

        view.movie_name_TV.text = movies[p0].show_title
        return view
    }

    override fun getItem(p0: Int): Any {
        return movies[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return movies.size
    }

}