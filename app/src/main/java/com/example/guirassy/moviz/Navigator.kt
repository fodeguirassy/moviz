package com.example.guirassy.moviz

import android.app.Fragment
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.model.User


interface Navigator {

    fun displayLoginScreen()
    fun displayMovieListScreen(director : Director)
    fun displayMovieDetailsScreen(movie: Movie)
    fun displayPreviousScreen()
    fun getUserFromPrefs() : User?
    fun saveUserInPrefs(user : User)
    fun displayUserProfileScreen(user:User)
    fun switchFabIcon()
}