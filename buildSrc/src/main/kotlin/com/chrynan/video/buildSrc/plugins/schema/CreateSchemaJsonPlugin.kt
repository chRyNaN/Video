package com.chrynan.video.buildSrc.plugins.schema

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register

class CreateSchemaJsonPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val extension = target.extensions.create<CreateSchemaJsonExtension>("graphQLSchemaJson")

        target.tasks.register<CreateSchemaJsonTask>("createSchemaJson", extension)
    }
}