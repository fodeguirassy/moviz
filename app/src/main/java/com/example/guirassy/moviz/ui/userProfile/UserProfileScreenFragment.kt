package com.example.guirassy.moviz.ui.userProfile


import android.os.Bundle
import android.view.View
import com.ekino.mvp.MvpFragment
import com.example.guirassy.moviz.MainActivity
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.R.id.user_imageView
import com.example.guirassy.moviz.R.id.user_saved_movies_RV
import com.example.guirassy.moviz.model.Movie
import com.example.guirassy.moviz.model.User
import com.example.guirassy.moviz.perisistence.Preferences
import java.text.SimpleDateFormat
import java.util.*

class UserProfileScreenFragment : MvpFragment<UserProfileScreenContract.Presenter>(),
        UserProfileScreenContract.View {

    override val defaultLayout: Int = R.layout.fragment_user_profile_screen
    lateinit private var user: User

    private val controller = UserMoviesController()

    override fun setUserMoviesList(movies: List<Movie>) {
        controller.setData(movies)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments.getSerializable("user") as User
    }

    companion object {
        private val USER_TAG = "user"
        fun newInstance(user: User): UserProfileScreenFragment {
            var fragment = UserProfileScreenFragment()
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
        (activity as MainActivity).toggle.isDrawerIndicatorEnabled = false
        actionBar?.title = "${user.name}"
        loadUserInfo(view)

        user_saved_movies_RV.adapter = controller.adapter
        user_saved_movies_RV.layoutManager = LinearLayoutManager(context)

        EpoxyTouchHelper.initDragging(controller)
                .withRecyclerView(user_saved_movies_RV)
                .forVerticalList()
                .withTarget(EpoxyModel::class.java)
                .andCallbacks(
                        object : EpoxyTouchHelper.DragCallbacks<EpoxyModel<*>>() {
                            override fun onModelMoved(fromPosition: Int, toPosition: Int, modelBeingMoved: EpoxyModel<*>?, itemView: View) {
                                Collections.swap(user.savedMovies, fromPosition, toPosition)
                                val tmpUserMovies = user.savedMovies
                                user.savedMovies = tmpUserMovies
                                Preferences.saveUserInPrefs(user)
                                user = Preferences.retrieveUserFromPrefs()!!
                                controller.setData(user.savedMovies)
                            }
                        })


        EpoxyTouchHelper.initSwiping(user_saved_movies_RV)
                .leftAndRight()
                .withTarget(EpoxyModel::class.java)
                .andCallbacks(
                        object : EpoxyTouchHelper.SwipeCallbacks<EpoxyModel<*>>(){
                            override fun onSwipeCompleted(model: EpoxyModel<*>?, itemView: View?, position: Int, direction: Int) {
                                user.savedMovies.removeAt(position)
                                Preferences.saveUserInPrefs(user)
                                user = Preferences.retrieveUserFromPrefs()!!
                                controller.setData(user.savedMovies)
                            }
                        }
                )
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
        if (user.savedMovies.isNotEmpty()) {
            user_saved_movies_default_msg.visibility = View.GONE
        }

    }

}