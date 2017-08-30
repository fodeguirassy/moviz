package com.example.guirassy.moviz.ui.userProfile


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.ekino.mvp.MvpFragment
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.User
import kotlinx.android.synthetic.main.fragment_user_profile_screen.*
import java.text.SimpleDateFormat
import java.util.*

class UserProfileScreenFragment : MvpFragment<UserProfileScreenContract.Presenter>(),
        UserProfileScreenContract.View {
    override val defaultLayout: Int = R.layout.fragment_user_profile_screen
    lateinit private var user : User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments.getSerializable("user") as User
    }

    companion object {
        private val USER_TAG = "user"
        fun newInstance(user : User) : UserProfileScreenFragment {
            var fragment  = UserProfileScreenFragment()
            val args = Bundle()
            args.putSerializable(USER_TAG, user)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "${user.name}"
        loadUserInfo(view)

    }

    private fun loadUserInfo(view: View) {
        Glide.with(view.context)
                .load(user.pictureUrl)
                .placeholder(R.drawable.app_icon)
                .into(user_imageView)

        val StringToDateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
        val dateToStringFormatter = SimpleDateFormat("EEEE dd MMMM yyyy", Locale.FRENCH)

        var userBirthDay = StringToDateFormatter.parse(user.birthday)
        user_birthday_TV.text = dateToStringFormatter.format(userBirthDay)

        user_saved_movies_default_msg.text = getString(R.string.user_saved_movies_defaultMsg)
        if(user.savedMovies.isEmpty()){
            user_saved_movies_default_msg.visibility = View.GONE
        }


    }

}