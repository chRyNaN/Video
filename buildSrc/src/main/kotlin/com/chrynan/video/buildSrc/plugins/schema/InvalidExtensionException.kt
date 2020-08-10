package com.chrynan.video.buildSrc.plugins.schema

import com.chrynan.video.buildSrc.plugins.schema.CreateSchemaJsonExtension

class InvalidExtensionException(extension: CreateSchemaJsonExtension?) :
    RuntimeException("Invalid extension provided. extension = $extension")