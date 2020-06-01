package com.chrynan.video.resources

import com.chrynan.video.model.ResourceID

interface ResourceAccessor {

    fun string(resourceID: ResourceID): Lazy<String>
}