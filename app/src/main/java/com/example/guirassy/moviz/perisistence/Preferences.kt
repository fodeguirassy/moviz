package com.example.guirassy.moviz.perisistence

import android.content.Context
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.model.User
import com.google.gson.Gson


class Preferences {

    companion object {
        lateinit var user : User
        private val PREFS = "PREFS_USER"
        private val USER_TAG = "user"
        private val gson = Gson()

        fun setUser(context: Context){
            user  = retrieveUserFromPrefs(context)!!
        }

        fun saveUserInPrefs(context: Context,user : User) {
            val sharedPrefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            val editor = sharedPrefs.edit()
            val jsonUser = gson.toJson(user)
            editor.putString(PREFS,jsonUser)
            editor.commit()
        }

        fun retrieveUserFromPrefs(context: Context) : User? {
            val sharedPrefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            sharedPrefs.all
            if(sharedPrefs.getString(USER_TAG, null) != null){
                val jsonUser = sharedPrefs.getString(USER_TAG, null)
                return gson.fromJson(jsonUser, User::class.java)
            }else{
                return null
            }
        }

        fun addMovie(context: Context, movie : Movie){
            setUser(context)
            user.savedMovies.add(movie)
            saveUserInPrefs(context, user)
        }
    }
}