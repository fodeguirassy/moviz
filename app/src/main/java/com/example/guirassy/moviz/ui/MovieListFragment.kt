package com.example.guirassy.moviz.ui


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.guirassy.moviz.R
import com.ekino.mvp.MvpFragment
import com.example.guirassy.moviz.adapter.MovieListAdapter
import com.example.guirassy.moviz.model.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : MvpFragment<MovieListContract.Presenter>(),
        MovieListContract.View {

    override val defaultLayout: Int = R.layout.fragment_movie_list
    lateinit var movieListAdapter: MovieListAdapter


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setMoviesList(movies: List<Movie>) {

        movieListAdapter = MovieListAdapter(this, movies)
        movies_list_view.adapter = movieListAdapter
        movies_list_view.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter.moviePressed(movies[p2])
            }
        }

    }



}
