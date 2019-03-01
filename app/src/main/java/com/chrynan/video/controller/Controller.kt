package com.chrynan.video.controller

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.chrynan.video.controller.tab.Tab

interface Controller<T : Tab> {

    val currentTab: T

    val currentTabStackCount: Int

    val isAtRootTabFragment: Boolean

    val currentDialogFragment: DialogFragment?

    var lastSavedInstanceState: Bundle?

    fun startAtTab(tab: T)

    fun switchToTab(tab: T)

    fun pushFragment(fragment: Fragment)

    fun popFragment()

    fun popFragments(count: Int)

    fun replaceFragment(fragment: Fragment)

    fun goBackToRootTabFragment()

    fun showDialogFragment(dialogFragment: DialogFragment)

    fun clearDialogFragment()
}