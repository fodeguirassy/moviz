package com.example.guirassy.moviz.ui.movieDetails


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ekino.mvp.MvpFragment
import com.example.guirassy.moviz.MainActivity
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.perisistence.Preferences
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_movie_details_screen.*

class MovieDetailsScreenFragment : MvpFragment<MovieDetailsScreenContract.Presenter>(),
        MovieDetailsScreenContract.View {
    
    override val defaultLayout: Int = R.layout.fragment_movie_details_screen
    private val MOVIE_TAG = "movie"
    lateinit var movie : Movie

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments.getSerializable(MOVIE_TAG) as Movie
    }

    companion object {
        fun newInstance(movie : Movie) : MovieDetailsScreenFragment {
            val fragment = MovieDetailsScreenFragment()
            val args = Bundle()
            args.putSerializable("movie", movie)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = movie.show_title


        (activity as MainActivity).toggle.isDrawerIndicatorEnabled = false

        (activity as MainActivity).fab.setImageResource(R.drawable.movie_add)
        (activity as MainActivity).fab.setOnClickListener {
            Preferences.addMovie(movie)
            Toast.makeText(context, "Your movie has been successfully added to your preferences", Toast.LENGTH_LONG).show()
            //Snackbar.make(view, "Your movie has been successfully added to your preferences", Snackbar.LENGTH_LONG)
        }

        Glide.with(view.context)
                .load(movie.poster)
                .into(movie_poster)

        movie_description.text = movie.summary
        movie_title.text = movie.show_title

    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).fab.setImageResource(R.drawable.account)
        (activity as MainActivity).fab.setOnClickListener {
            presenter.displayLoginScreen()
        }
    }
}