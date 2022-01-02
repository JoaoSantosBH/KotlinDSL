object Versioner {

    private const val majorNumber = 1
    private const val minorNumber = 0
    private const val patch = 0
    private val buildNumber = System.getenv("JENKINS_BUILD_NUMBER")?.toInt() ?: 1

    val versionCode: Int by lazy {
        println("Version code assigned to $buildNumber")
        return@lazy buildNumber
    }

    val versionName: String by lazy {
        val versionName = "$majorNumber.$minorNumber.$patch.$buildNumber"
        println("Version name assigned to $versionName")
        return@lazy versionName
    }
}