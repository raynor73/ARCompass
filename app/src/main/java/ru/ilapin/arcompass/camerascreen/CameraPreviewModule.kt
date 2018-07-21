package ru.ilapin.arcompass.camerascreen

import android.view.View
import dagger.Module
import dagger.Provides
import ru.ilapin.arcompass.PerActivity

@Module
class CameraPreviewModule(private val rootView: View) {

    @Provides
    @PerActivity
    fun providePresenter() = CameraPreviewPresenter(rootView)

    @Provides
    @PerActivity
    fun provideController(presenter: CameraPreviewPresenter) = CameraPreviewController(presenter)
}