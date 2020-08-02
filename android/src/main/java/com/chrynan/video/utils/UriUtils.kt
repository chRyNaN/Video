package com.chrynan.video.utils

import android.net.Uri
import com.chrynan.common.model.core.UriString

fun Uri.asUriString(): UriString = toString()

fun UriString.asAndroidUri(): Uri = Uri.parse(this)

fun UriString.decodeToAndroidUri(): Uri = Uri.parse(Uri.decode(this))