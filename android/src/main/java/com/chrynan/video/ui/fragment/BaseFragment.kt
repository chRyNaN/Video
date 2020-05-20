package com.chrynan.video.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.chrynan.kotlinutils.perform
import com.chrynan.presentation.navigator.Navigator
import com.chrynan.presentation.presenter.BasePresenter
import com.chrynan.presentation.view.SnackbarView
import com.chrynan.video.R
import com.chrynan.video.coroutine.FragmentCoroutineScope
import com.chrynan.video.ui.activity.BaseActivity
import com.chrynan.video.utils.snackbarOf
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : DaggerFragment(),
    FragmentCoroutineScope,
    Navigator,
    SnackbarView {

    override val coroutineContext: CoroutineContext
        get() = lifecycleScope.coroutineContext

    protected open val presenter: BasePresenter? = null

    private var currentSnackbar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter?.bind()
    }

    override fun onResume() {
        super.onResume()

        presenter?.bind()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter?.unbind()

        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        presenter?.unbind()

        super.onPause()
    }

    override fun onDestroyView() {
        presenter?.unbind()

        super.onDestroyView()
    }

    override fun goBack() {
        (activity as? BaseActivity)?.goBack()
    }

    override fun showSnackbar(
        message: String,
        type: SnackbarView.Type,
        length: SnackbarView.Length,
        action: SnackbarView.Action?
    ) {
        currentSnackbar = snackbarOf(
            view = view,
            message = message,
            type = type,
            length = length,
            action = action
        )

        currentSnackbar?.show()
    }

    override fun hideSnackbar() {
        currentSnackbar?.dismiss()
    }

    fun onRefresh() {}

    protected fun goToFragment(
        fragment: BaseFragment,
        fragmentContainerId: Int = R.id.fragmentContainer
    ) {
        (activity as? BaseActivity)?.goToFragment(
            fragment = fragment,
            fragmentContainerId = fragmentContainerId
        )
    }

    protected fun startActivitySafely(f: (Context) -> Intent) =
        context.perform { startActivity(f(this)) }

    protected fun startActivitySafelyAndFinish(f: (Context) -> Intent) {
        startActivitySafely(f).also {
            activity?.finish()
        }
    }
}