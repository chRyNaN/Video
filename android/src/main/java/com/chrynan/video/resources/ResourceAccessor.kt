package com.chrynan.video.resources

import android.graphics.drawable.Drawable
import com.chrynan.video.presentation.model.ResourceID

interface ResourceAccessor {

    fun string(resourceID: ResourceID): Lazy<String>

    fun drawable(resourceID: ResourceID): Lazy<Drawable?>
}