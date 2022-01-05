package com.example.testegradle.gradle

import AppParams
import AppVersions
import Libraries
import Versions
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Project '$name' is not an Android module")

val Project.java: JavaPluginExtension
    get() = extensions.findByName("java") as? JavaPluginExtension
        ?: error("Project '$name' is not an Java module")

fun Project.androidApplicationConfig(
    appId: String = AppParams.applicationId,
    appVersionCode: Int = AppVersions.versionCode,
    appVersionName: String = AppVersions.versionName,
    defaultConfigExtensions: (DefaultConfig.() -> Unit)? = null,
    androidExtensions: (BaseExtension.() -> Unit)? = null
) {
    androidLibraryConfig(
        {
            applicationId = appId
            versionCode = appVersionCode
            versionName = appVersionName
            defaultConfigExtensions?.invoke(this)
        },
        androidExtensions
    )
}

fun Project.androidLibraryConfig(
    defaultConfigExtensions: (DefaultConfig.() -> Unit)? = null,
    androidExtensions: (BaseExtension.() -> Unit)? = null
) {
    android.run {
        compileSdkVersion(AppParams.compileSdkVersion)
        defaultConfig {
            defaultConfigExtensions?.invoke(this)
            minSdk = (AppParams.minSdkVersion)
            targetSdk = (AppParams.targetSdkVersion)
            multiDexEnabled = true
            testInstrumentationRunner = Libraries.android_test_runner_implementation_class
        }

                buildTypes {
                    getByName("debug") {
                        debuggable(true)
                        versionNameSuffix = "-dev"
                        isMinifyEnabled = false
                        setMatchingFallbacks("release")
                    }
                    getByName("release") {
                        debuggable(false)
                        isMinifyEnabled = true
                        proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
                        consumerProguardFiles("consumer-rules.pro")
                    }

                }
                viewBinding {
                    android.buildFeatures.viewBinding = true
                }

                compileOptions {
                    isCoreLibraryDesugaringEnabled = true
                    sourceCompatibility = Versions.java_compatibility
                    targetCompatibility = Versions.java_compatibility
                }

                packagingOptions.exclude("META-INF/main.kotlin_module")

        androidExtensions?.invoke(this)
    }
    setupTests()
    setupKotlinOptions()
}

fun Project.kotlinLibraryConfig() {
    java.run {
        sourceCompatibility = Versions.java_compatibility
        targetCompatibility = Versions.java_compatibility
    }

    setupTests()
    setupKotlinOptions()
}

fun Project.dependenciesConfig(
    dependencyExtensions: (DependencyHandlerScope.() -> Unit)? = null
) {
    dependencies {
        kotlinProject()
        unitTestDependencies()
        dependencyExtensions?.invoke(this)
    }
}

fun Project.setupTests() {
    tasks.withType<Test> {

        useJUnitPlatform()

        testLogging {
            lifecycle {
                events = mutableSetOf(
                    TestLogEvent.FAILED,
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.STANDARD_OUT
                )
                exceptionFormat = TestExceptionFormat.FULL
                showExceptions = true
                showCauses = true
                showStackTraces = true
                showStandardStreams = true
            }
            info.events = lifecycle.events
            info.exceptionFormat = lifecycle.exceptionFormat
        }

        val failedTests = mutableListOf<TestDescriptor>()
        val skippedTests = mutableListOf<TestDescriptor>()

        addTestListener(object : TestListener {
            override fun beforeSuite(suite: TestDescriptor) {}
            override fun beforeTest(testDescriptor: TestDescriptor) {}
            override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
                when (result.resultType) {
                    TestResult.ResultType.FAILURE -> failedTests.add(testDescriptor)
                    TestResult.ResultType.SKIPPED -> skippedTests.add(testDescriptor)
                    else -> Unit
                }
            }

            override fun afterSuite(suite: TestDescriptor, result: TestResult) {
                if (suite.parent == null) { // root suite
                    logger.lifecycle("----")
                    logger.lifecycle("Test result: ${result.resultType}")
                    logger.lifecycle(
                        "Test summary: ${result.testCount} tests, " +
                                "${result.successfulTestCount} succeeded, " +
                                "${result.failedTestCount} failed, " +
                                "${result.skippedTestCount} skipped"
                    )
                    failedTests.takeIf { it.isNotEmpty() }?.prefixedSummary("\tFailed Tests")
                    skippedTests.takeIf { it.isNotEmpty() }?.prefixedSummary("\tSkipped Tests:")
                }
            }

            private infix fun List<TestDescriptor>.prefixedSummary(subject: String) {
                logger.lifecycle(subject)
                forEach { test -> logger.lifecycle("\t\t${test.displayName()}") }
            }

            private fun TestDescriptor.displayName() = parent?.let { "${it.name} - $name" } ?: name
        })
    }
}

fun Project.setupKotlinOptions() {
    tasks.withType<KotlinCompile> {
        kotlinOptions {

            apiVersion = Versions.kotlin_jvm_target
            languageVersion = Versions.kotlin_jvm_target
            jvmTarget = Versions.java_jvm_target.toString()

            freeCompilerArgs = listOf(
                "-Xjvm-default=compatibility",
                "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xuse-experimental=kotlinx.coroutines.FlowPreview",
                "-Xuse-experimental=kotlin.Experimental",
                "-Xinline-classes"
            )
        }
    }
}
