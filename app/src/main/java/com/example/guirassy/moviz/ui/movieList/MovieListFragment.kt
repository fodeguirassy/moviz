package com.example.guirassy.moviz.ui.movieList


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.guirassy.moviz.R
import com.ekino.mvp.MvpFragment
import com.example.guirassy.moviz.model.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : MvpFragment<MovieListContract.Presenter>(),
        MovieListContract.View {

    override val defaultLayout: Int = R.layout.fragment_movie_list
    lateinit private var movieListAdapter: MovieListAdapter


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setMoviesList(movies: List<Movie>) {

        movieListAdapter = MovieListAdapter(this, movies)
        movies_list_view.adapter = movieListAdapter
        movies_list_view.onItemClickListener = AdapterView.OnItemClickListener { _, _, p2, _ -> presenter.moviePressed(movies[p2]) }

    }



}