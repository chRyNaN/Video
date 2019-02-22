package com.chrynan.video.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrynan.video.R
import com.chrynan.video.ui.fragment.VideoFragment
import com.chrynan.video.ui.transition.CollapsingVideoTransitionStateListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar?.let { setSupportActionBar(it) }

        val videoFragment = VideoFragment()

        videoMotionLayout?.videoTransitionStateListeners =
                listOf(CollapsingVideoTransitionStateListener(context = this, videoView = videoFragment))

        supportFragmentManager.beginTransaction().add(R.id.videoFragmentContainer, videoFragment).commit()
    }
}