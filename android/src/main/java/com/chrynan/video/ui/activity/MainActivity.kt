package com.chrynan.video.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.chrynan.video.ui.view.TopMenuView
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.MainScreen
import com.chrynan.video.ui.widget.BaseExpandableOverlayWidget
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainScreen>(),
    TopMenuView,
    BottomNavigationView.OnNavigationItemSelectedListener,
    BaseExpandableOverlayWidget.ProgressChangedListener {

    companion object {

        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override var topMenuTitle: CharSequence?
        get() = null
        set(value) {

        }

    override var showTopMenu: Boolean
        get() = false
        set(value) {

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBottomNavigationView?.setOnNavigationItemSelectedListener(this)

        goTo(MainScreen.Home)
    }

    override fun goTo(screen: MainScreen) {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_bottom_navigation_home -> goTo(MainScreen.Home)
            R.id.action_bottom_navigation_search -> goTo(MainScreen.Search)
            R.id.action_bottom_navigation_settings -> goTo(MainScreen.Settings)
        }

        // There's an odd issue with the overlays when changing fragments, so this is a hack to fix it
        mainContainerLayout?.progress = BaseExpandableOverlayWidget.PROGRESS_COLLAPSED

        return true
    }

    override fun onProgressChanged(progress: Float) {
        //mainContainerLayout?.progress = progress
    }
}