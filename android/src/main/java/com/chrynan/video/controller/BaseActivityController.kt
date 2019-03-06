package com.chrynan.video.controller

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.chrynan.video.controller.provider.TabFragmentProvider
import com.chrynan.video.controller.tab.Tab
import com.chrynan.video.ui.activity.BaseActivity
import com.ncapdevi.fragnav.FragNavController

open class BaseActivityController<T : Tab>(
    private val activity: BaseActivity,
    @IdRes containerId: Int,
    private val tabProvider: TabFragmentProvider<T>
) : Controller<T>,
    FragNavController.RootFragmentListener {

    private val fragNavController = FragNavController(activity.supportFragmentManager, containerId)

    override lateinit var currentTab: T
        protected set

    override val currentTabStackCount: Int
        get() = fragNavController.currentStack?.size ?: 1

    override val isAtRootTabFragment: Boolean
        get() = fragNavController.isRootFragment

    override val currentDialogFragment: DialogFragment?
        get() = fragNavController.currentDialogFrag

    override var lastSavedInstanceState: Bundle? = null

    override fun startAtTab(tab: T) {
        fragNavController.rootFragmentListener = this
        currentTab = tab
        fragNavController.initialize(tab.index, lastSavedInstanceState)
    }

    override fun switchToTab(tab: T) {
        currentTab = tab
        fragNavController.switchTab(tab.index)
    }

    override fun pushFragment(fragment: Fragment) = fragNavController.pushFragment(fragment)

    override fun popFragment() {
        fragNavController.popFragment()
    }

    override fun popFragments(count: Int) = fragNavController.popFragments(count)

    override fun replaceFragment(fragment: Fragment) = fragNavController.replaceFragment(fragment)

    override fun goBackToRootTabFragment() = fragNavController.clearStack()

    override fun showDialogFragment(dialogFragment: DialogFragment) =
        fragNavController.showDialogFragment(dialogFragment)

    override fun clearDialogFragment() = fragNavController.clearDialogFragment()

    override val numberOfRootFragments = tabProvider.rootTabCount

    override fun getRootFragment(index: Int) =
        tabProvider.getTabFromIndex(index)?.let { tabProvider.getRootTabFragment(it) }
            ?: throw IllegalStateException("Incorrect Tab Index.")
}