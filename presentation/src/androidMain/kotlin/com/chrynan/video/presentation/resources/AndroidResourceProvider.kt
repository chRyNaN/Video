package com.chrynan.video.presentation.resources

import android.content.Context
import android.graphics.drawable.Drawable
import com.chrynan.video.presentation.model.ResourceID

class AndroidResourceProvider(private val context: Context) : AndroidResourceAccessor {

    override fun string(resourceID: ResourceID): Lazy<String> =
        lazy { context.resources.getString(resourceID) }

    override fun drawable(resourceID: ResourceID): Lazy<Drawable?> =
        lazy { context.resources.getDrawable(resourceID, null) }
}