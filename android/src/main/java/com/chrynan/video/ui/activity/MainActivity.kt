package com.chrynan.video.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.chrynan.expandable.ExpandableChildLayout
import com.chrynan.expandable.ExpandableContainerView
import com.chrynan.expandable.ExpandableState
import com.chrynan.expandable.ExpandableStateListener
import com.chrynan.video.navigator.MainNavigator
import com.chrynan.video.ui.view.TopMenuView
import com.chrynan.video.R
import com.chrynan.video.ui.fragment.*
import com.chrynan.video.ui.widget.BaseExpandableOverlayWidget
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
    TopMenuView,
    ExpandableContainerView,
    MainNavigator,
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

    override val currentExpandableState: ExpandableState
        get() = ExpandableState.Collapsed // expandableLayout?.currentExpandableState ?: ExpandableState.Collapsed

    override val expandableChildLayout: ExpandableChildLayout?
        get() = null // videoFragmentContainer

    override val endAsExpanded = true

    override var expandedInteractionView: View?
        get() = expandableChildLayout?.expandedInteractionView
        set(value) {
            expandableChildLayout?.expandedInteractionView = value
        }

    override var collapsedInteractionView: View?
        get() = expandableChildLayout?.collapsedInteractionView
        set(value) {
            expandableChildLayout?.collapsedInteractionView = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBottomNavigationView?.setOnNavigationItemSelectedListener(this)

        goToHome()

        goToVideo()
    }

    override fun addStateListener(listener: ExpandableStateListener) {
        // expandableLayout?.expandableStateListeners?.add(listener)
    }

    override fun removeStateListener(listener: ExpandableStateListener) {
        // expandableLayout?.expandableStateListeners?.remove(listener)
    }

    override fun expand() {
        // expandableLayout?.expand()
    }

    override fun collapse() {
        // expandableLayout?.collapse()
    }

    override fun goToHome() = goToFragment(HomeFragment.newInstance())

    override fun goToSearch() = goToFragment(SearchFragment.newInstance())

    override fun goToUserContent() = goToFragment(UserContentFragment.newInstance())

    override fun goToSettings() = goToFragment(SettingsFragment.newInstance())

    override fun goToVideo() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_bottom_navigation_home -> goToHome()
            R.id.action_bottom_navigation_search -> goToSearch()
            R.id.action_bottom_navigation_settings -> goToSettings()
        }

        // There's an odd issue with the overlays when changing fragments, so this is a hack to fix it
        mainContainerLayout?.progress = BaseExpandableOverlayWidget.PROGRESS_COLLAPSED

        return true
    }

    override fun onProgressChanged(progress: Float) {
        //mainContainerLayout?.progress = progress
    }
}