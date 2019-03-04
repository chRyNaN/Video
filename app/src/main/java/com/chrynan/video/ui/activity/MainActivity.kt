package com.chrynan.video.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.chrynan.video.R
import com.chrynan.video.controller.Controller
import com.chrynan.video.controller.tab.MainTabs
import com.chrynan.video.navigator.MainNavigator
import com.chrynan.video.ui.fragment.VideoFragment
import com.chrynan.video.ui.view.TopMenuView
import com.chrynan.video.ui.widget.expandable.ExpandableChildLayout
import com.chrynan.video.ui.widget.expandable.ExpandableContainerView
import com.chrynan.video.ui.widget.expandable.ExpandableState
import com.chrynan.video.ui.widget.expandable.ExpandableStateListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_video.view.*
import javax.inject.Inject

class MainActivity : BaseActivity(),
    TopMenuView,
    ExpandableContainerView,
    MainNavigator,
    BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    override lateinit var controller: Controller<MainTabs>

    override var topMenuTitle: CharSequence?
        get() = toolbar?.title
        set(value) {
            toolbar?.title = value
        }

    override var showTopMenu: Boolean
        get() = toolbar?.let { (it.visibility == View.VISIBLE) and (it.alpha != 0f) } ?: false
        set(value) {
            toolbar?.animate()
                ?.alpha(if (value) 1f else 0f)
                ?.start()
        }

    override val currentExpandableState: ExpandableState
        get() = expandableLayout?.currentExpandableState ?: ExpandableState.Collapsed

    override val expandableChildLayout: ExpandableChildLayout?
        get() = videoFragmentContainer

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

        controller.startAtTab(MainTabs.HOME)

        toolbar?.let { setSupportActionBar(it) }

        bottomNavigationView?.setOnNavigationItemSelectedListener(this)

        val videoFragment = VideoFragment()

        supportFragmentManager.beginTransaction().add(R.id.videoFragmentContainer, videoFragment).commit()

        videoFragmentContainer?.apply {
            collapsedInteractionView = this
            expandedInteractionView = this.videoPlayerView
        }

        goToHome()
    }

    override fun addStateListener(listener: ExpandableStateListener) {
        expandableLayout?.expandableStateListeners?.add(listener)
    }

    override fun removeStateListener(listener: ExpandableStateListener) {
        expandableLayout?.expandableStateListeners?.remove(listener)
    }

    override fun expand() {
        expandableLayout?.expand()
    }

    override fun collapse() {
        expandableLayout?.collapse()
    }

    override fun goToHome() = controller.switchToTab(MainTabs.HOME)

    override fun goToSearch() = controller.switchToTab(MainTabs.SEARCH)

    override fun goToSettings() = controller.switchToTab(MainTabs.SETTINGS)

    override fun goBack() {
        /*
        if (mainMotionLayout?.isExpanded == true) {
            mainMotionLayout?.collapse()
        } else {
            super.goBack()
        }*/
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> goToHome()
            R.id.action_search -> goToSearch()
            R.id.action_settings -> goToSettings()
        }

        return true
    }
}