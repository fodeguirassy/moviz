package com.example.guirassy.moviz.ui

import com.example.guirassy.moviz.Navigator
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.platform.NetflixRService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


/**
 * Created by guirassy on 14/08/2017.
 */
class MovieListPresenterTest {

    lateinit var view : MovieListContract.View
    lateinit private var movieListPresenter : MovieListPresenter
    lateinit private var netFlexRService : NetflixRService
    lateinit private var navigator : Navigator


    @Before
    fun setUp() {
        netFlexRService =  mock <NetflixRService>{}
        navigator = Mockito.spy(Navigator::class.java)
        view = Mockito.spy(MovieListContract.View::class.java)
        movieListPresenter = MovieListPresenter(view,navigator,netFlexRService, "Quentin Tarantino")
    }

    @Test
    fun resume() {


    }

    @Test
    fun moviePressed() {
        //val movie = Movie("Kill Bill 2")
        //movieListPresenter.moviePressed(movie)
        //verify(navigator).displayMovieDetailsScreen(movie)
    }

}