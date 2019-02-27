package com.chrynan.video.ui.activity

import android.os.Bundle
import android.view.View
import com.chrynan.video.R
import com.chrynan.video.navigator.MainNavigator
import com.chrynan.video.ui.fragment.VideoFragment
import com.chrynan.video.ui.transition.CollapsingVideoTransitionStateListener
import com.chrynan.video.ui.view.TopMenuView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
    TopMenuView,
    MainNavigator {

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

        val videoFragment = VideoFragment()

        mainMotionLayout?.apply {
            progress = 1f
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
    }

    override fun goToHome() {

    }

    override fun goToSearch() {
    }

    override fun goToSettings() {
    }

    override fun goBack() {

    }
}