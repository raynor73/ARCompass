package ru.ilapin.arcompass.compassscreen

import android.content.Context
import android.view.View
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent
import dagger.Module
import dagger.Provides
import io.reactivex.Flowable
import ru.ilapin.arcompass.PerActivity
import javax.inject.Named

@Module
class CompassScreenModule(private val rootView: View) {

    @Provides
    @PerActivity
    fun providePresenter(): CompassScreenPresenter {
        return CompassScreenPresenter(rootView)
    }

    @Provides
    @PerActivity
    fun provideController(
            context: Context,
            presenter: CompassScreenPresenter,
            @Named("Accelerometer") accelerometer: Flowable<ReactiveSensorEvent>,
            @Named("MagneticField") magneticField: Flowable<ReactiveSensorEvent>
    ) = CompassController(context, presenter, accelerometer, magneticField)
}