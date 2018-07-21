package ru.ilapin.arcompass.camerascreen

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import ru.ilapin.arcompass.App
import ru.ilapin.arcompass.PermissionsModule
import ru.ilapin.arcompass.R
import javax.inject.Inject

class CameraPreviewActivity : AppCompatActivity() {

    @Inject
    lateinit var rxPermissions: RxPermissions

    @Inject
    lateinit var controller: CameraPreviewController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_preview)
        App.appComponent.cameraPreviewComponent(
                CameraPreviewModule(findViewById(R.id.container)),
                PermissionsModule(this)
        ).inject(this)

        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe { granted ->

                }
    }
}
