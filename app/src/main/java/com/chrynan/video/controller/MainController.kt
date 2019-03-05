package com.chrynan.video.controller

import androidx.annotation.IdRes
import com.chrynan.video.controller.provider.MainTabsProvider
import com.chrynan.video.controller.tab.MainTabs
import com.chrynan.video.ui.activity.MainActivity
import javax.inject.Inject

class MainController @Inject constructor(
    activity: MainActivity,
    @IdRes containerId: Int,
    tabProvider: MainTabsProvider
) : BaseActivityController<MainTabs>(
    activity = activity,
    containerId = containerId,
    tabProvider = tabProvider
), Controller<MainTabs>