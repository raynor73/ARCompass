package ru.ilapin.arcompass

import android.content.Context
import android.hardware.SensorManager
import android.opengl.Matrix
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent
import io.reactivex.Flowable
import io.reactivex.Notification
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import ru.ilapin.common.math.Vector4f

typealias CombinedSensorEvents = BiFunction<Notification<ReactiveSensorEvent>, Notification<ReactiveSensorEvent>, Pair<Notification<ReactiveSensorEvent>, Notification<ReactiveSensorEvent>>>

class CompassController(
        private val context: Context,
        private val presenter: CompassPresenter,
        private val accelerometer: Flowable<ReactiveSensorEvent>,
        private val magneticField: Flowable<ReactiveSensorEvent>
) {
    private var sensorsSubscription: Disposable? = null

    private val idleVector = Vector4f(0.0f, 100.0f, 0.0f)

    private val transformedVector = Vector4f()

    private val rotationMatrix = FloatArray(16)

    fun startSensorReadings() {
        sensorsSubscription = Flowable.combineLatest(
                accelerometer.materialize(),
                magneticField.materialize(),
                CombinedSensorEvents {a, m -> Pair(a, m)}
        ).subscribe { p ->
            val accelerometerError = if (p.first.isOnError) {
                context.getString(R.string.no_accelerometer_error_message)
            } else {
                null
            }

            val magneticFieldError = if (p.second.isOnError) {
                context.getString(R.string.no_magnetometer_error_message)
            } else {
                null
            }

            if (accelerometerError != null || magneticFieldError != null) {
                presenter.showError(accelerometerError, magneticFieldError)
            } else {
                presenter.showReadings()

                val a = p.first.value ?: return@subscribe
                val m = p.second.value ?: return@subscribe

                SensorManager.getRotationMatrix(
                        rotationMatrix,
                        null,
                        a.sensorEvent.values,
                        m.sensorEvent.values
                )

                Matrix.multiplyMV(transformedVector.values, 0, rotationMatrix, 0, idleVector.values, 0)

                presenter.drawVector(transformedVector)
            }
        }
    }

    fun stopSensorReadings() {
        sensorsSubscription?.dispose()
    }
}