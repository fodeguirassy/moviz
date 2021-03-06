package com.example.guirassy.moviz

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.ekino.ekinodemo.AppFragmentNavigator
import com.example.guirassy.moviz.model.Director
import com.example.guirassy.moviz.model.User
import com.example.guirassy.moviz.perisistence.Preferences
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuFragment
import com.example.guirassy.moviz.ui.welcomeMenu.WelcomeMenuPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navigator: Navigator
    lateinit var toggle: ActionBarDrawerToggle

    private var user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navigator = AppFragmentNavigator(this, supportFragmentManager, R.id.fragment_container)
        navigator.displayMovieListScreen(Director("Quentin Tarantino", R.drawable.tarantino_bis))

        initializeMenuFragment()

        fab.setOnClickListener { view ->
            /*
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            */
            navigator.displayLoginScreen()
        }

        toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        toggle.setToolbarNavigationClickListener {
            onBackPressed()
        }


        Preferences.initSharedPreferences(this)

    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
        return true
    }


    override fun onBackPressed() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        if (this.drawer_layout.isDrawerOpen(GravityCompat.START)) {
            this.drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private fun initializeMenuFragment() {
        val welcomeMenuFragment = WelcomeMenuFragment()
        val welcomeMenuPresenter = WelcomeMenuPresenter(welcomeMenuFragment, navigator)
        welcomeMenuFragment.presenter = welcomeMenuPresenter
        supportFragmentManager.beginTransaction().replace(R.id.menu_container, welcomeMenuFragment).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == R.id.action_settings){
            user  = Preferences.retrieveUserFromPrefs()as User
            navigator.displayUserProfileScreen( user)
            true
        }else{
            super.onOptionsItemSelected(item)
        }
    }
}
