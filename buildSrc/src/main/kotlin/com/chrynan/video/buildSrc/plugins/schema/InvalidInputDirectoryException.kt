package com.chrynan.video.buildSrc.plugins.schema

class InvalidInputDirectoryException(path: String? = null) :
    RuntimeException("Invalid input directory. Make sure the provided path is a directory. path = $path")