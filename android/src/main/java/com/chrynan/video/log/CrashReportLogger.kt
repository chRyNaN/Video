package com.chrynan.video.log

import com.chrynan.loggertimber.TimberLogger
import javax.inject.Inject

class CrashReportLogger @Inject constructor() : TimberLogger() {

    override fun init() {
        // TODO Log the Crash
    }
}