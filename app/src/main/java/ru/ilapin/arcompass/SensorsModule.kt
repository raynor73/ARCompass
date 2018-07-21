package ru.ilapin.arcompass

import android.content.Context
import android.hardware.Sensor
import com.github.pwittchen.reactivesensors.library.ReactiveSensorFilter
import com.github.pwittchen.reactivesensors.library.ReactiveSensors
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class SensorsModule {

    @Provides
    @Singleton
    @Named("Accelerometer")
    fun provideAccelerometer(context: Context) =
            ReactiveSensors(context)
                    .observeSensor(Sensor.TYPE_ACCELEROMETER)
                    .filter(ReactiveSensorFilter.filterSensorChanged())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())

    @Provides
    @Singleton
    @Named("MagneticField")
    fun provideMagneticField(context: Context) =
            ReactiveSensors(context)
                    .observeSensor(Sensor.TYPE_MAGNETIC_FIELD)
                    .filter(ReactiveSensorFilter.filterSensorChanged())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
}