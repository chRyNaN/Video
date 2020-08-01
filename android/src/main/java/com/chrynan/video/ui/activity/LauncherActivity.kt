package com.chrynan.video.ui.activity

import android.os.Bundle
import com.chrynan.video.navigator.LauncherNavigator

class LauncherActivity : BaseActivity(),
    LauncherNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        goToMainScreen()

        finish()
    }

    override fun goToMainScreen() = startActivity(MainActivity.newIntent(this))
}