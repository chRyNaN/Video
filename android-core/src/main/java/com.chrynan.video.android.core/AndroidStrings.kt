package com.chrynan.video.android.core

import com.chrynan.video.presentation.resources.Strings

class AndroidStrings(resourceAccessor: AndroidResourceAccessor) : Strings,
    AndroidResourceAccessor by resourceAccessor {

    override val settingsGroupTitleContent by string(R.string.settings_group_title_content)
    override val settingsGroupTitleAbout by string(R.string.settings_group_title_about)
    override val settingsItemTitleServices by string(R.string.settings_item_title_services)
    override val settingsItemTitleAddService by string(R.string.settings_item_title_add_service)
    override val settingsItemTitleApp by string(R.string.settings_item_title_app)
    override val settingsItemTitleSourceCode by string(R.string.settings_item_title_source_code)
    override val settingsItemTitleLicense by string(R.string.settings_item_title_license)
    override val settingsItemTitleOpenSourceLicenses by string(R.string.settings_item_title_open_source_licenses)
}