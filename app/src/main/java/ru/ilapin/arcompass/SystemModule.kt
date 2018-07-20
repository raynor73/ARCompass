package ru.ilapin.arcompass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author ilapin on 20.07.18.
 */
@Module
class SystemModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideSensorManager() = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    @Provides
    @Singleton
    @Named("Accelerometer")
    fun provideAccelerometer(sensorManager: SensorManager): Sensor? =
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    @Provides
    @Singleton
    @Named("MagneticField")
    fun provideMagneticField(sensorManager: SensorManager): Sensor? =
            sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
}