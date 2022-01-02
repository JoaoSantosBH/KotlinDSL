package com.example.testegradle.gradle

import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

fun RepositoryHandler.jitPack() {
    maven {
        url = URI.create("https://jitpack.io")
    }
}
