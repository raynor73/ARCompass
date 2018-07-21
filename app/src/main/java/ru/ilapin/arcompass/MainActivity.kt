package ru.ilapin.arcompass

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.OnClick
import ru.ilapin.arcompass.camerascreen.CameraActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var controller: CompassController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.compassComponent(CompassModule(findViewById(R.id.container))).inject(this)
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
        startActivity(Intent(this, CameraActivity::class.java))
    }
}
