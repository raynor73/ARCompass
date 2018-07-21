package ru.ilapin.arcompass

import android.content.Context
import android.view.View
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent
import dagger.Module
import dagger.Provides
import io.reactivex.Flowable
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
            @Named("Accelerometer") accelerometer: Flowable<ReactiveSensorEvent>,
            @Named("MagneticField") magneticField: Flowable<ReactiveSensorEvent>
    ) = CompassController(context, presenter, accelerometer, magneticField)
}