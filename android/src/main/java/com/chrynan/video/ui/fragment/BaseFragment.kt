package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.View
import com.chrynan.video.navigator.Navigator
import com.chrynan.video.presenter.Presenter
import com.chrynan.video.ui.activity.BaseActivity
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(),
    Navigator {

    open val presenter: Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onAttachToView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.onDetachFromView()
    }

    override fun goBack() {
        (activity as? BaseActivity)?.goBack() ?: activity?.onBackPressed()
    }
}