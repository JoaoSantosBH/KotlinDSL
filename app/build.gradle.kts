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

        val buildconfigProperties: Properties by rootProject.extra

        flavorDimensions("version")

        productFlavors {

            create("free") {
                dimension = "version"
                buildConfigField("String", "baseUrl",
                    buildconfigProperties.getProperty("paidEndpoint"))

            }
            create("paid") {
                dimension = "version"
                versionNameSuffix = ".paid"
                buildConfigField("String", "baseUrl",
                    buildconfigProperties.getProperty("freeEndpoint"))
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