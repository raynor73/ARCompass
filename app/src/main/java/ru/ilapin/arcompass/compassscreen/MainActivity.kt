package ru.ilapin.arcompass.compassscreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import ru.ilapin.arcompass.App
import ru.ilapin.arcompass.R
import ru.ilapin.arcompass.camerascreen.CameraPreviewActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var controller: CompassController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        App.appComponent.compassScreenComponent(CompassScreenModule(findViewById(R.id.container))).inject(this)
    }

    override fun onResume() {
        super.onResume()
        controller.startSensorReadings()
    }

    override fun onPause() {
        super.onPause()
        controller.stopSensorReadings()
    }

    @OnClick(R.id.camera_button)
    fun onCameraButtonClick() {
        startActivity(Intent(this, CameraPreviewActivity::class.java))
    }
}
