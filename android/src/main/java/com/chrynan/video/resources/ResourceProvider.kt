package com.chrynan.video.resources

import com.chrynan.video.di.qualifier.ApplicationContextQualifier
import com.chrynan.video.model.ResourceID
import com.chrynan.video.utils.ApplicationContext
import javax.inject.Inject

class ResourceProvider @Inject constructor(@ApplicationContextQualifier private val context: ApplicationContext) :
    ResourceAccessor {

    override fun string(resourceID: ResourceID): Lazy<String> =
        lazy { context.resources.getString(resourceID) }
}