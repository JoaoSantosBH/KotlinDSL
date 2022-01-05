package com.example.testegradle.gradle

import Libraries
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.kotlinProject() {
    "implementation"(Libraries.kotlin_stdlib)
}
fun DependencyHandlerScope.desugaringDependencies() {
    "coreLibraryDesugaring"(Libraries.android_tools_desugar_jdk)
}

fun DependencyHandlerScope.unitTestDependencies() {
    "testImplementation"(Libraries.android_junit)
}

fun DependencyHandlerScope.androidTestDependencies() {
    "androidTestImplementation"(Libraries.android_junit)
    "androidTestImplementation"(Libraries.espresso_core)
    "androidTestImplementation"(Libraries.android_test_core)
}