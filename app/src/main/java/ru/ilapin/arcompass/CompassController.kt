package ru.ilapin.arcompass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.opengl.Matrix
import ru.ilapin.common.math.Vector4f

class CompassController(
        private val context: Context,
        private val presenter: CompassPresenter,
        private val sensorManager: SensorManager,
        private val accelerometer: Sensor?,
        private val magneticField: Sensor?
) {

    private val idleVector = Vector4f(0.0f, 100.0f, 0.0f)
    private val transformedVector = Vector4f()

    private var isAccelerometerReadingsAvailable = false
    private val accelerometerReading = FloatArray(3)
    private var isMagnetometerReadingsAvailable = false
    private val magnetometerReading = FloatArray(3)

    private val rotationMatrix = FloatArray(16)

    private val sensorListener = object : SensorEventListener {

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // do nothing
        }

        override fun onSensorChanged(event: SensorEvent) {
            when (event.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> {
                    System.arraycopy(event.values, 0, accelerometerReading, 0, accelerometerReading.size)
                    isAccelerometerReadingsAvailable = true
                }

                Sensor.TYPE_MAGNETIC_FIELD -> {
                    System.arraycopy(event.values, 0, magnetometerReading, 0, magnetometerReading.size)
                    isMagnetometerReadingsAvailable = true
                }

                else -> IllegalArgumentException("Unexpected sensor type: ${event.sensor.type}")
            }

            redrawVector()
        }
    }

    init {
        val sb = StringBuilder()
        if (accelerometer == null) {
            sb.append(context.getString(R.string.no_accelerometer_error_message))
        }
        if (magneticField == null) {
            if (sb.isNotEmpty()) {
                sb.append("\n")
            }

            sb.append(context.getString(R.string.no_magnetometer_error_message))
        }
        if (sb.isNotEmpty()) {
            presenter.showError(sb.toString())
        } else {
            presenter.showOrientation()
        }

        presenter.drawVector(idleVector)
    }

    fun startSensorReadings() {
        sensorManager.registerListener(
                sensorListener,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_UI
        )
        sensorManager.registerListener(
                sensorListener,
                magneticField,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_UI
        )
    }

    fun stopSensorReadings() {
        sensorManager.unregisterListener(sensorListener)
    }

    private fun redrawVector() {
        if (isAccelerometerReadingsAvailable && isMagnetometerReadingsAvailable) {
            SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magnetometerReading)

            Matrix.multiplyMV(transformedVector.values, 0, rotationMatrix, 0, idleVector.values, 0);

            presenter.drawVector(transformedVector)
        }
    }
}