@file:Suppress("SpellCheckingInspection")
import org.gradle.api.JavaVersion

object Versions {

    // Plugins
    const val kotlin_jvm_target = "1.6"
    const val kotlin = "1.5.20"
    const val android_tools_build = "7.0.0"
    const val android_tools_desugar_jdk = "1.0.10"
    val java_jvm_target = JavaVersion.VERSION_1_8
    val java_compatibility = JavaVersion.VERSION_1_8

    // Commons
    const val core_ktx = "1.7.0"
    const val appcompat = "1.4.0"
    const val material = "1.4.0"
    const val constraintlayout = "2.1.2"
    const val navigation = "2.3.5"

    // Test
    const val android_junit = "1.1.3"
    const val espresso_core = "3.4.0"
    const val android_test = "1.3.0"

}