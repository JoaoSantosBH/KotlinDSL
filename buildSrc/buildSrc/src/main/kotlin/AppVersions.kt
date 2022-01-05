object AppVersions {

    private const val majorNumber = 1
    private const val minorNumber = 0
    private val buildNumber =
        System.getenv("JENKINS_BUILD_NUMBER")?.toInt() ?: 1

    val versionCode: Int by lazy {
        println("Version code ===>  $buildNumber")
        return@lazy buildNumber
    }

    val versionName: String by lazy {
        val versionName = "$majorNumber.$minorNumber.$buildNumber"
        println("Version name ===>  $versionName")
        return@lazy versionName
    }

}