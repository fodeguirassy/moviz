package com.ekino.mvp

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Copyright (c) 2017 Ekino
 */

abstract class MvpFragment<P : BasePresenter> : Fragment(), BaseView<P> {

    private val TAG = "ekino-mvp.fragment"

    private val uuid: String

    lateinit var presenter: P

    abstract val defaultLayout: Int

    init {
        this.uuid = UUID.randomUUID().toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(defaultLayout, container, false)
        return view
    }

    @CallSuper
    override fun onResume() {
        Log.v(TAG, "onResume(): " + this.toString())
        super.onResume()
        presenter.resume()
    }

    @CallSuper
    override fun onPause() {
        Log.v(TAG, "onPause(): " + this.toString())
        presenter.pause()
        super.onPause()
    }

    override fun onDestroy() {
        Log.v(TAG, "onDestroy(): " + this.toString())
        super.onDestroy()
    }

    val fragmentTag: String
        get() = this.javaClass.simpleName + "-" + uuid

    fun onBackPressed(): Boolean {
        return false
    }

}
