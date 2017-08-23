package com.example.guirassy.moviz.ui.movieList


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import com.example.guirassy.moviz.R
import com.ekino.mvp.MvpFragment
import com.example.guirassy.moviz.MainActivity
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : MvpFragment<MovieListContract.Presenter>(),
        MovieListContract.View {

    override val defaultLayout: Int = R.layout.fragment_movie_list
    lateinit private var movieListAdapter: MovieListAdapter
    lateinit private var director : Director
    private val DIRECTOR_TAG = "director"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        director = arguments.getSerializable(DIRECTOR_TAG) as Director
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        var actionBar = (activity as AppCompatActivity).supportActionBar
        (activity as MainActivity).toggle.isDrawerIndicatorEnabled = true
        actionBar?.title = "${director.name}"
    }

    override fun setMoviesList(movies: List<Movie>) {
        movieListAdapter = MovieListAdapter(this, movies)
        movies_list_view.adapter = movieListAdapter
        movies_list_view.onItemClickListener = AdapterView.OnItemClickListener { _, _, p2, _ -> presenter.moviePressed(movies[p2]) }
    }

    companion object {
        fun newInstance(director : Director) : MovieListFragment{
            var fragment = MovieListFragment()
            val args = Bundle()
            args.putSerializable("director", director)
            fragment.arguments = args
            return fragment
        }
    }
}
