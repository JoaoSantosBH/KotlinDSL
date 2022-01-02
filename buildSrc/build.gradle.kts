plugins {
    `kotlin-dsl`
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}

gradlePlugin {
    plugins {
        register("android-library-plugin") {
            id = "android-library-plugin"
            implementationClass =
                "com.example.testegradle.gradle.plugins.AndroidLibraryPlugin"
        }
        register("kotlin-library-plugin") {
            id = "kotlin-library-plugin"
            implementationClass =
                "com.example.testegradle.gradle.plugins.KotlinLibraryPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(Libraries.kotlin_gradle_plugin)
    implementation(Libraries.tools_build_gradle_plugin)
}