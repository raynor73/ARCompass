package ru.ilapin.arcompass.camerascreen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import ru.ilapin.arcompass.App
import ru.ilapin.arcompass.R
import javax.inject.Inject

class CameraPreviewActivity : AppCompatActivity() {

    companion object {

        private const val CAMERA_PERMISSION_REQUEST_CODE = 123
    }

    @Inject
    lateinit var controller: CameraPreviewController

    private var isCameraPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_preview)
        App.appComponent
                .cameraPreviewComponent(CameraPreviewModule(findViewById(R.id.container), windowManager))
                .inject(this)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            isCameraPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            isCameraPermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onResume() {
        super.onResume()

        if (isCameraPermissionGranted) {
            controller.startCameraPreview()
        } else {
            controller.onCameraAccessForbidden()
        }
    }

    override fun onPause() {
        super.onPause()

        if (isCameraPermissionGranted) {
            controller.stopCameraPreview()
        }
    }
}
