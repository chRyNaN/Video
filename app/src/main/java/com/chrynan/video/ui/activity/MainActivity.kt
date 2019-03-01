package com.chrynan.video.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.chrynan.video.R
import com.chrynan.video.navigator.MainNavigator
import com.chrynan.video.ui.fragment.HomeFragment
import com.chrynan.video.ui.fragment.SearchFragment
import com.chrynan.video.ui.fragment.SettingsFragment
import com.chrynan.video.ui.fragment.VideoFragment
import com.chrynan.video.ui.transition.CollapsingVideoTransitionStateListener
import com.chrynan.video.ui.view.TopMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
    TopMenuView,
    MainNavigator,
    BottomNavigationView.OnNavigationItemSelectedListener {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar?.let { setSupportActionBar(it) }

        bottomNavigationView?.setOnNavigationItemSelectedListener(this)

        val videoFragment = VideoFragment()

        mainMotionLayout?.apply {
            videoContainerView = videoFragmentContainer
            videoTransitionStateListeners =
                    listOf(
                        CollapsingVideoTransitionStateListener(
                            context = this@MainActivity,
                            videoView = videoFragment
                        )
                    )
        }

        supportFragmentManager.beginTransaction().add(R.id.videoFragmentContainer, videoFragment).commit()

        goToHome()
    }

    override fun goToHome() {
        supportFragmentManager.beginTransaction().replace(R.id.baseFragmentContainer, HomeFragment.newInstance())
            .commit()
    }

    override fun goToSearch() {
        supportFragmentManager.beginTransaction().replace(R.id.baseFragmentContainer, SearchFragment.newInstance())
            .commit()
    }

    override fun goToSettings() {
        supportFragmentManager.beginTransaction().replace(R.id.baseFragmentContainer, SettingsFragment.newInstance())
            .commit()
    }

    override fun goBack() {
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