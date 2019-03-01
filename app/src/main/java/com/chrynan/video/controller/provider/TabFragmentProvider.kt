package com.chrynan.video.controller.provider

import androidx.fragment.app.Fragment
import com.chrynan.video.controller.tab.Tab

interface TabFragmentProvider<T : Tab> {

    val rootTabCount: Int

    fun getTabFromIndex(index: Int): T?

    fun getRootTabFragment(tab: T): Fragment
}