package com.chrynan.video.presentation.resources

import android.graphics.drawable.Drawable
import com.chrynan.video.presentation.model.ResourceID

interface AndroidResourceAccessor {

    fun string(resourceID: ResourceID): Lazy<String>

    fun drawable(resourceID: ResourceID): Lazy<Drawable?>
}