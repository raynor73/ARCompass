package ru.ilapin.arcompass.camerascreen

import dagger.Module
import dagger.Provides
import ru.ilapin.arcompass.PerActivity

@Module
class CameraModule {

    @Provides
    @PerActivity
    fun provideController() = CameraController()
}