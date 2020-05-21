package com.chrynan.video.ui.activity

import android.os.Bundle
import androidx.lifecycle.coroutineScope
import com.chrynan.video.R
import com.chrynan.video.coroutine.ActivityCoroutineScope
import com.chrynan.video.navigator.Navigator
import com.chrynan.video.presenter.BasePresenter
import com.chrynan.video.ui.fragment.BaseFragment
import com.chrynan.video.ui.view.View
import dagger.android.support.DaggerAppCompatActivity
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : DaggerAppCompatActivity(),
    ActivityCoroutineScope,
    Navigator,
    View {

    override val coroutineContext: CoroutineContext
        get() = lifecycle.coroutineScope.coroutineContext

    protected open val presenter: BasePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter?.bind()
    }

    override fun onRestart() {
        super.onRestart()

        presenter?.bind()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter?.unbind()

        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        presenter?.unbind()

        super.onDestroy()
    }

    override fun goBack() {
        with(supportFragmentManager) {
            if (!isStateSaved && backStackEntryCount > 0) {
                popBackStack()
            } else {
                defaultGoBack()
            }
        }
    }

    fun goToFragment(
        fragment: BaseFragment,
        fragmentContainerId: Int = R.id.fragmentContainer
    ) {
        supportFragmentManager.let {
            it.beginTransaction().apply {
                replace(fragmentContainerId, fragment)

                commitNow()
            }
        }
    }

    private fun defaultGoBack() = super.onBackPressed()
}