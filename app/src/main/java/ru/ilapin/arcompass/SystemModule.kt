package ru.ilapin.arcompass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author ilapin on 20.07.18.
 */
@Module
class SystemModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    fun provideSensorManager() = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    @Provides
    @Named("Accelerometer")
    fun provideAccelerometer(sensorManager: SensorManager) =
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    @Provides
    @Named("MagneticField")
    fun provideMagneticField(sensorManager: SensorManager) =
            sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
}