package com.chrynan.video.controller.provider

import com.chrynan.video.controller.tab.MainTabs
import com.chrynan.video.ui.fragment.ChannelFragment
import com.chrynan.video.ui.fragment.HomeFragment
import com.chrynan.video.ui.fragment.SearchFragment
import com.chrynan.video.ui.fragment.SettingsFragment
import javax.inject.Inject

class MainTabsProvider @Inject constructor() : TabFragmentProvider<MainTabs> {

    override val rootTabCount = MainTabs.values().size

    override fun getTabFromIndex(index: Int) = MainTabs.values().firstOrNull { it.index == index }

    override fun getRootTabFragment(tab: MainTabs) =
        when (tab) {
            MainTabs.HOME -> HomeFragment.newInstance()
            MainTabs.SEARCH -> SearchFragment.newInstance()
            MainTabs.USER_CONTENT -> ChannelFragment.newInstance()
            MainTabs.SETTINGS -> SettingsFragment.newInstance()
        }
}