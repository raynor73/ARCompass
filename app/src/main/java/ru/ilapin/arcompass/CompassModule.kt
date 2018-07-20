package ru.ilapin.arcompass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.view.View
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class CompassModule(private val rootView: View) {

    @Provides
    @PerActivity
    fun providePresenter(): CompassPresenter {
        return CompassPresenter(rootView)
    }

    @Provides
    @PerActivity
    fun provideController(
            context: Context,
            presenter: CompassPresenter,
            sensorManager: SensorManager,
            @Named("Accelerometer") accelerometer: Sensor?,
            @Named("MagneticField") magneticField: Sensor?
    ) = CompassController(context, presenter, sensorManager, accelerometer, magneticField)
}