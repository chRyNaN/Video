package com.chrynan.video.utils

import com.chrynan.common.model.Uri

fun android.net.Uri.asUri(): Uri = Uri(
    scheme = scheme!!,
    authority = authority,
    userInfo = userInfo,
    host = host,
    port = port,
    path = path!!,
    query = query,
    fragment = fragment
)