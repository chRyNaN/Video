package com.chrynan.video.ui.activity

import android.os.Bundle
import com.chrynan.presentation.navigator.Navigator
import com.chrynan.video.controller.Controller
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(),
    Navigator {

    protected abstract val controller: Controller<*>

    private var lastSavedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lastSavedInstanceState = savedInstanceState
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        controller.lastSavedInstanceState = outState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        lastSavedInstanceState = savedInstanceState
    }

    override fun onBackPressed() = goBack()

    override fun goBack() {
        if (controller.isAtRootTabFragment) super.onBackPressed() else controller.popFragment()
    }
}