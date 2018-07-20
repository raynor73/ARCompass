package ru.ilapin.arcompass

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SensorEventListener {

    @Inject
    lateinit var sensorManager: SensorManager

    @Inject
    //@Named("Accelerometer")
    @JvmField
    var accelerometer: Sensor? = null

    @Inject
    //@Named("MagneticField")
    @JvmField
    var magneticField: Sensor? = null

    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        App.appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()

        /*sensorManager.registerListener(
                this,
                Sensor.TYPE_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_UI
        )
        sensorManager.registerListener(this, Sensor.TYPE_MAGNETIC_FIELD,
                SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI)*/
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {

    }
}
