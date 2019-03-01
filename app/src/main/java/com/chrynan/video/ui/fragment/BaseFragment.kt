package com.chrynan.video.ui.fragment

import com.chrynan.video.navigator.Navigator
import com.chrynan.video.ui.activity.BaseActivity
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(),
    Navigator {

    override fun goBack() {
        (activity as? BaseActivity)?.goBack() ?: activity?.onBackPressed()
    }
}