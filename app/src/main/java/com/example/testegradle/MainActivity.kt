package com.example.testegradle

import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import showToast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pInfo: PackageInfo =
            this.applicationContext.packageManager
                .getPackageInfo(this.applicationContext.packageName, 0)

        showToast(pInfo.versionName)

    }
}