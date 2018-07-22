package ru.ilapin.arcompass.camerascreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.ilapin.arcompass.App
import ru.ilapin.arcompass.LifecycleModule
import ru.ilapin.arcompass.PermissionsModule
import ru.ilapin.arcompass.R

class CameraPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_preview)
        App.appComponent.cameraPreviewComponent(
                CameraPreviewModule(findViewById(R.id.container)),
                PermissionsModule(this),
                LifecycleModule(this)
        ).inject(this)
    }
}
