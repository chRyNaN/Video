package com.chrynan.video.ui.activity

import android.os.Bundle
import com.chrynan.video.presentation.navigator.LauncherScreen

class LauncherActivity : BaseActivity<LauncherScreen>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        goTo(LauncherScreen.Main)
    }

    override fun goTo(screen: LauncherScreen) {
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}