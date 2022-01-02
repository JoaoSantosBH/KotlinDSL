package com.example.testegradle.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.example.testegradle.gradle.kotlinLibraryConfig
import com.example.testegradle.gradle.dependenciesConfig

class KotlinLibraryPlugin: Plugin<Project> {

    override fun apply(project: Project) =
        with(project) {
            applyPlugins()
            kotlinLibraryConfig()
            dependenciesConfig()
        }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("org.jetbrains.kotlin.jvm")
        }
    }
}