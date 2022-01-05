object Libraries {

    //Kotlin section
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    //Core section
    const val android_tools_desugar_jdk = "com.android.tools:desugar_jdk_libs:${Versions.android_tools_desugar_jdk}"

    //Gradle plugins section
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val tools_build_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_tools_build}"
    const val navigation_safe_args_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    //Navigation section
    const val navigation_fragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    //Presentation section
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    // Tests section
    const val android_test_runner_implementation_class = "androidx.test.runner.AndroidJUnitRunner"
    const val android_junit = "androidx.test.ext:junit:${Versions.android_junit}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    const val android_test_core = "androidx.test:core:${Versions.android_test}"

}
