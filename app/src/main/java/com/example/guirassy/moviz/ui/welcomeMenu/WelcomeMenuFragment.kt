package com.example.guirassy.moviz.ui.welcomeMenu


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ekino.mvp.MvpFragment
import com.example.guirassy.moviz.MainActivity
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Director
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_welcome_menu.*

class WelcomeMenuFragment : MvpFragment<WelcomeMenuContract.Presenter>(),
        WelcomeMenuContract.View {

    override val defaultLayout: Int = R.layout.fragment_welcome_menu
    private val controller = WelcomeMenuController({
        director ->
            onDirectorSelected(director)
    })

    override fun setWelcomeMenu(directors: List<Director>) {
        controller.setData(directors)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        welcome_menu_recyclerView.adapter = controller.adapter
        welcome_menu_recyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
    }

    private fun onDirectorSelected(director: Director) {
        presenter.onDirectorSelected(director)
        (activity as MainActivity).drawer_layout.closeDrawers()
    }

}
