import com.example.testegradle.gradle.androidApplicationConfig
import com.example.testegradle.gradle.androidTestDependencies
import com.example.testegradle.gradle.dependenciesConfig
import com.example.testegradle.gradle.desugaringDependencies

import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

androidApplicationConfig(
    defaultConfigExtensions = {
        buildConfigField("Boolean", "useMockServer", "false")
    },
    androidExtensions = {

        val environmentProperties: Properties by rootProject.extra

        flavorDimensions("version")

        productFlavors {

            create("paid") {
                dimension = "version"
            }
            create("free") {
                dimension = "version"
                versionNameSuffix = ".paid"
            }
        }
    }
)

dependenciesConfig {
    androidTestDependencies()
    desugaringDependencies()
    implementation(Libraries.material)
    implementation(Libraries.core_ktx)
    implementation(Libraries.appcompat)
    implementation(Libraries.constraintlayout)
    implementation(Libraries.navigation_fragment_ktx)
    implementation(Libraries.navigation_ui_ktx)
}