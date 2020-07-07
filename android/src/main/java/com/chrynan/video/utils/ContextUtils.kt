package com.chrynan.video.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources

typealias ApplicationContext = Context
typealias ActivityContext = Context

val Resources.isOrientationLandscape: Boolean
    get() = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

val Resources.isOrientationPortrait: Boolean
    get() = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

val Context.isOrientationLandscape: Boolean
    get() = resources.isOrientationLandscape

val Context.isOrientationPortrait: Boolean
    get() = resources.isOrientationPortrait