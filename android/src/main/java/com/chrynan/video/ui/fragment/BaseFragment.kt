package com.chrynan.video.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.chrynan.kotlinutils.perform
import com.chrynan.video.R
import com.chrynan.video.coroutine.FragmentCoroutineScope
import com.chrynan.video.presentation.navigator.Navigator
import com.chrynan.video.presentation.navigator.Screen
import com.chrynan.video.presentation.presenter.BasePresenter
import com.chrynan.video.presentation.state.Change
import com.chrynan.video.presentation.state.Intent
import com.chrynan.video.presentation.state.State
import com.chrynan.video.presentation.view.View
import com.chrynan.video.ui.activity.BaseActivity
import dagger.android.support.DaggerFragment
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<INTENT : Intent, STATE : State, CHANGE : Change, SCREEN : Screen> :
    DaggerFragment(),
    View<INTENT, STATE>,
    Navigator<SCREEN>,
    FragmentCoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = lifecycleScope.coroutineContext

    protected open val presenter: BasePresenter<INTENT, STATE, CHANGE>? = null

    protected val currentState: STATE
        get() = presenter?.currentState ?: renderState

    protected lateinit var renderState: STATE

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
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

    override fun render(state: STATE) {
        renderState = state
    }

    override fun goBack() {
        (activity as? BaseActivity<*>)?.goBack()
    }

    fun onRefresh() {}

    protected fun goToFragment(
        fragment: BaseFragment<*, *, *, *>,
        fragmentContainerId: Int = R.id.fragmentContainer
    ) {
        (activity as? BaseActivity<*>)?.goToFragment(
            fragment = fragment,
            fragmentContainerId = fragmentContainerId
        )
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun startActivitySafely(f: (Context) -> android.content.Intent) =
        context.perform { startActivity(f(this)) }

    protected fun startActivitySafelyAndFinish(f: (Context) -> android.content.Intent) {
        startActivitySafely(f).also {
            activity?.finish()
        }
    }
}