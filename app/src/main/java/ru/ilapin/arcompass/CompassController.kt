package ru.ilapin.arcompass

import android.content.Context
import android.hardware.SensorManager
import android.opengl.Matrix
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import ru.ilapin.common.math.Vector4f

class CompassController(
        private val context: Context,
        private val presenter: CompassPresenter,
        private val accelerometer: Flowable<ReactiveSensorEvent>,
        private val magneticField: Flowable<ReactiveSensorEvent>
) {
    private var sensorsSubscription: Disposable? = null

    private val errorStringBuilder = StringBuilder()

    private val idleVector = Vector4f(0.0f, 100.0f, 0.0f)

    private val transformedVector = Vector4f()

    private val rotationMatrix = FloatArray(16)

    fun startSensorReadings() {
        presenter.showOrientation()
        errorStringBuilder.setLength(0)

        sensorsSubscription = Flowable.zip(
                accelerometer.doOnError {
                    val msg = context.getString(R.string.no_accelerometer_error_message)
                    if (errorStringBuilder.isEmpty()) {
                        errorStringBuilder.append(msg)
                    } else {
                        errorStringBuilder.insert(0, "\n").insert(0, msg)
                    }
                    presenter.showError(errorStringBuilder.toString())

                },
                magneticField.doOnError {
                    val msg = context.getString(R.string.no_magnetometer_error_message)
                    if (errorStringBuilder.isEmpty()) {
                        errorStringBuilder.append(msg)
                    } else {
                        errorStringBuilder.append("\n").append(msg)
                    }
                    presenter.showError(errorStringBuilder.toString())
                },
                BiFunction<ReactiveSensorEvent, ReactiveSensorEvent, Unit> {
                    accelerometerReading, magnetometerReading ->

                    SensorManager.getRotationMatrix(
                            rotationMatrix,
                            null,
                            accelerometerReading.sensorEvent.values,
                            magnetometerReading.sensorEvent.values
                    )

                    Matrix.multiplyMV(transformedVector.values, 0, rotationMatrix, 0, idleVector.values, 0)

                    presenter.drawVector(transformedVector)
                }
        ).subscribe()
    }

    fun stopSensorReadings() {
        sensorsSubscription?.dispose()
    }
}