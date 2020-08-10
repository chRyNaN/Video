package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.core.UriString

data class ServiceProviderListItemViewModel(
    val uri: UriString,
    val name: String,
    val imageUri: UriString? = null
) : AdapterItem {

    override val uniqueAdapterId = "ServiceProviderListItem:$uri".asUniqueAdapterId()
}