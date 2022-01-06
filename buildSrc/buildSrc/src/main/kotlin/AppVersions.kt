object AppVersions {

    private const val majorNumber = 1
    private const val minorNumber = 0
    private const val patch = 0
    private const val buildNumber = 1

    val versionCode: Int by lazy {
        println("Version code ===>  $buildNumber")
        return@lazy buildNumber
    }

    val versionName: String by lazy {
        val versionName = "$majorNumber.$minorNumber.$patch.$buildNumber"
        println("Version name ===>  $versionName")
        return@lazy versionName
    }

}