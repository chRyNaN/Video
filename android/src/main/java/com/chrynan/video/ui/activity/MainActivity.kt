package com.chrynan.video.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.chrynan.expandable.ExpandableChildLayout
import com.chrynan.expandable.ExpandableContainerView
import com.chrynan.expandable.ExpandableState
import com.chrynan.expandable.ExpandableStateListener
import com.chrynan.kotlinutils.isTruthy
import com.chrynan.video.navigator.MainNavigator
import com.chrynan.video.ui.view.TopMenuView
import com.chrynan.video.R
import com.chrynan.video.controller.VideoPlayerController
import com.chrynan.video.controller.tab.VideoPlayerTabs
import com.chrynan.video.ui.fragment.HomeFragment
import com.chrynan.video.ui.fragment.SearchFragment
import com.chrynan.video.ui.fragment.SettingsFragment
import com.chrynan.video.ui.fragment.UserContentFragment
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
    lateinit var videoPlayerController: VideoPlayerController

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

        toolbar?.let { setSupportActionBar(it) }

        bottomNavigationView?.setOnNavigationItemSelectedListener(this)

        videoFragmentContainer?.apply {
            collapsedInteractionView = this
            expandedInteractionView = this.videoPlayerView
        }

        videoPlayerController.startAtTab(VideoPlayerTabs.VIDEO)

        goToHome()

        goToVideo()
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

    override fun goToHome() = goToFragment(HomeFragment.newInstance())

    override fun goToSearch() = goToFragment(SearchFragment.newInstance())

    override fun goToUserContent() = goToFragment(UserContentFragment.newInstance())

    override fun goToSettings() = goToFragment(SettingsFragment.newInstance())

    override fun goToVideo() {
        // Later, when you need to pass in parameters, consider using sealed classes
        // instead of enum classes as tabs? Maybe that way you can provide information to them? Not sure if that would work
        videoPlayerController.switchToTab(VideoPlayerTabs.VIDEO)
    }

    override fun goBack() {
        if (expandableLayout?.currentExpandableState?.isExpanded.isTruthy()) {
            expandableLayout?.collapse()
        } else {
            super.goBack()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> goToHome()
            R.id.action_search -> goToSearch()
            R.id.action_user_content -> goToUserContent()
            R.id.action_settings -> goToSettings()
        }

        return true
    }
}