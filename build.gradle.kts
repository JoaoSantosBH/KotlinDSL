
buildscript {

    val inputStream = project.rootDir.resolve("buildconfig.properties").inputStream()
    val buildConfigParams = java.util.Properties().apply { inputStream.use { file -> load(file) } }

    mapOf(
        "buildconfigProperties" to buildConfigParams
    ).entries.forEach {
        project.extra.set(it.key, it.value)
    }

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Libraries.tools_build_gradle_plugin)
        classpath(Libraries.kotlin_gradle_plugin)
        classpath(Libraries.navigation_safe_args_plugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
        group = "cleanup"
    }
}