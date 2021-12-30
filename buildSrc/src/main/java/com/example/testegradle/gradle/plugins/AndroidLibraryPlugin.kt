package com.example.testegradle.gradle.plugins

import com.example.testegradle.gradle.androidLibraryConfig
import com.example.testegradle.gradle.desugaringDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) =
        with(project) {
            applyPlugins()

            androidLibraryConfig(
                androidExtensions = {
                    testOptions {
                        animationsDisabled = true
                        unitTests.apply {
                            isReturnDefaultValues = true
                            isIncludeAndroidResources = true
                            tasks.withType<Test>().all {
                                jvmArgs("-noverify")
                            }
                        }
                    }
                }

            )
            
        }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("com.android.library")
            apply("kotlin-android")
        }
    }
}
