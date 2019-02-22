package com.chrynan.video.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chrynan.video.navigator.Navigator

abstract class BaseActivity : AppCompatActivity(),
    Navigator {

    override fun goBack() =
        with(supportFragmentManager) {
            if (!isStateSaved && backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                onBackPressed()
            }
        }

    protected fun goToFragment(containerViewId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
        supportFragmentManager.let { fragmentManager ->
            fragmentManager.beginTransaction().let { fragmentTransaction ->

                fragmentTransaction.replace(containerViewId, fragment)

                if (addToBackStack) {
                    fragmentTransaction.addToBackStack(null)
                        .commit()
                        .run { fragmentManager.executePendingTransactions() }
                } else {
                    fragmentTransaction.commitNow()
                }
            }
        }
    }
}