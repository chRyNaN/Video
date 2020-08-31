package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.presentation.core.AdapterItem

data class ServiceProviderListItemViewModel(
    val uri: UriString,
    val name: String,
    val imageUri: UriString? = null
) : AdapterItem {

    override val uniqueAdapterId = "ServiceProviderListItem:$uri".asUniqueAdapterId()
}