package com.example.guirassy.moviz.perisistence

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.model.User
import com.google.gson.Gson


class Preferences {

    companion object {
        private lateinit var user : User
        private val PREFS = "PREFS_USER"
        private val USER_TAG = "user"
        private var gson = Gson()

        private lateinit var sharedPreferences : SharedPreferences
        private lateinit var sharedPrefEditor : SharedPreferences.Editor

        fun initSharedPreferences(context: Context){
            sharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            sharedPrefEditor = sharedPreferences.edit()
            user = retrieveUserFromPrefs()!!
        }
        fun saveUserInPrefs(user : User) {
            val jsonUser = gson.toJson(user)
            sharedPrefEditor.putString(USER_TAG,jsonUser)
            sharedPrefEditor.commit()
        }

        fun retrieveUserFromPrefs() : User? {
            return if(sharedPreferences.getString(USER_TAG, null) != null){
                val jsonUser = sharedPreferences.getString(USER_TAG, null)
                var res = gson.fromJson(jsonUser, User::class.java)
                res
            } else  null
        }
        fun addMovie(movie : Movie){
            if(!user.savedMovies.contains(movie)){
                user.savedMovies.add(movie)
                saveUserInPrefs(user)
            }
        }
    }
}