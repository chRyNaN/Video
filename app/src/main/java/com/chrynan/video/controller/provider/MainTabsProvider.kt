package com.chrynan.video.controller.provider

import com.chrynan.video.controller.tab.MainTabs
import com.chrynan.video.ui.fragment.HomeFragment
import com.chrynan.video.ui.fragment.SearchFragment
import com.chrynan.video.ui.fragment.SettingsFragment
import javax.inject.Inject

class MainTabsProvider @Inject constructor() : TabFragmentProvider<MainTabs> {

    override val rootTabCount = 3

    override fun getTabFromIndex(index: Int) = MainTabs.values().firstOrNull { it.index == index }

    override fun getRootTabFragment(tab: MainTabs) =
        when (tab) {
            MainTabs.HOME -> HomeFragment.newInstance()
            MainTabs.SEARCH -> SearchFragment.newInstance()
            MainTabs.SETTINGS -> SettingsFragment.newInstance()
        }
}