package com.ekino.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Copyright (c) 2017 Ekino
 */

/**
 * @param view Reference to the view implementing *Contract.View interface
 * @param navigator Reference to an App navigator
 */
abstract class
MvpPresenter<out N, V : BaseView<out BasePresenter>>(protected val view: V,
                                                     protected val navigator: N) : BasePresenter {

    private val subscriptions: CompositeDisposable

    init {
        subscriptions = CompositeDisposable()
    }

    override fun resume() {
        //noop
    }

    override fun pause() {
        disposeSubscriptions()
    }

    /**
     * A disposable added through this method will be automatically disposed when Presenter
     * is stopped.

     * @param disposable The Disposable (subscription) to add
     */
    protected fun addToAutoDisposeList(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    /**
     * Manually trigger clearing Rx Subscription added using {addToAutoDisposeList()}
     */
    protected fun disposeSubscriptions() {
        subscriptions.clear()
    }

}
