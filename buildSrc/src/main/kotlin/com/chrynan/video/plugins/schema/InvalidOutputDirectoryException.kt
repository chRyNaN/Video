package com.chrynan.video.plugins.schema

class InvalidOutputDirectoryException(path: String? = null) :
    RuntimeException("Invalid output directory. Make sure the provided path is a directory. path = $path")