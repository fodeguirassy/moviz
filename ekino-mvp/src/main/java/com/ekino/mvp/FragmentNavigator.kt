package com.ekino.mvp

import android.content.Context
import android.support.v4.app.FragmentManager
import android.util.Log


/**
 * Copyright (c) 2017 Ekino
 */

abstract class FragmentNavigator(protected val context: Context,
                                 protected val fragmentManager: FragmentManager,
                                 private val containerViewId: Int) {

    protected open val TAG = "ekino-mvp.navigator"


    fun onBackPressed(): Boolean {
        var handled = false

        val fm = fragmentManager

        val lastEntry = fm.getBackStackEntryCount() - 1

        var currentFragment: MvpFragment<*>? = null

        if (lastEntry >= 0) {
            val backEntry = fm.getBackStackEntryAt(lastEntry)
            val tag = backEntry.getName()

            val fragment = fm.findFragmentByTag(tag)
            if (fragment is MvpFragment<*>) {
                currentFragment = fragment
            }
        }

        if (currentFragment != null) {
            handled = currentFragment.onBackPressed()
        }

        return handled
    }

    /**
     *
     */
    abstract fun displayOrRestoreScreenOnActivityCreate()

    protected fun backStackHasFragments(): Boolean {
        return fragmentManager.getBackStackEntryCount() > 0
    }

    protected fun restoreLastFragment() {
        Log.d(TAG, "Restoring Last Fragment")
        val fragmentManager = fragmentManager
        val fragmentTag = fragmentManager
                .getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1)
                .getName()
        fragmentManager.popBackStack(fragmentTag, 0)
    }

    protected fun clearBackStack() {
        if (!backStackHasFragments()) {
            Log.d(TAG, "empty fragment backstack")
            return
        }

        val entry = fragmentManager.getBackStackEntryAt(0)
        if (entry == null) {
            Log.d(TAG, "null fragment backstack entry")
            return
        }

        fragmentManager.popBackStackImmediate(entry.getId(),
                FragmentManager.POP_BACK_STACK_INCLUSIVE)

        fragmentManager.executePendingTransactions()
    }

    protected fun addToBackStackWithSlideAnimation(newFragment: MvpFragment<*>) {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right)
                .replace(containerViewId, newFragment, newFragment.fragmentTag)
                .addToBackStack(newFragment.fragmentTag)
                .commit()
    }

    protected fun addToBackStackWithFadeAnimation(newFragment: MvpFragment<*>) {
        fragmentManager
                .beginTransaction()
                .replace(containerViewId, newFragment, newFragment.fragmentTag)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(newFragment.fragmentTag)
                .commit()
    }

    private fun addToBackStackWithoutAnimation(newFragment: MvpFragment<*>) {
        fragmentManager
                .beginTransaction()
                .replace(containerViewId, newFragment, newFragment.fragmentTag)
                .addToBackStack(newFragment.fragmentTag)
                .commit()
    }

    fun goBack() {
        fragmentManager.popBackStackImmediate()
    }

    protected fun displayAndSetRootFragment(fragment: MvpFragment<*>) {
        clearBackStack()

        fragmentManager
                .beginTransaction()
                .replace(containerViewId, fragment, fragment.fragmentTag)
                .commit();
    }

    abstract fun displayMovieList(directorName: String)
}