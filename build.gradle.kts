
buildscript {

    val envFile = project.rootDir.resolve("environment.properties").inputStream()
    val envProperties = java.util.Properties().apply { envFile.use { file -> load(file) } }

    mapOf(
        "environmentProperties" to envProperties
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