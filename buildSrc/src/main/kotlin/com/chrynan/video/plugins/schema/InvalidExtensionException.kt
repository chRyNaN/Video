package com.chrynan.video.plugins.schema

import com.chrynan.video.plugins.schema.CreateSchemaJsonExtension

class InvalidExtensionException(extension: CreateSchemaJsonExtension?) :
    RuntimeException("Invalid extension provided. extension = $extension")