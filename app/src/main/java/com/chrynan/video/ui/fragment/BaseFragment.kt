package com.chrynan.video.ui.fragment

import androidx.fragment.app.Fragment
import com.chrynan.video.navigator.Navigator
import com.chrynan.video.ui.activity.BaseActivity

abstract class BaseFragment : Fragment(),
    Navigator {

    override fun goBack() {
        (activity as? BaseActivity)?.goBack() ?: activity?.onBackPressed()
    }
}