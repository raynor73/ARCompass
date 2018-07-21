package ru.ilapin.arcompass.camerascreen

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import ru.ilapin.arcompass.App
import ru.ilapin.arcompass.PermissionsModule
import ru.ilapin.arcompass.R
import javax.inject.Inject

class CameraActivity : AppCompatActivity() {

    @Inject
    lateinit var rxPermissions: RxPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        App.appComponent.permissionsComponent(PermissionsModule(this)).inject(this)

        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe { granted ->

                }
    }
}
