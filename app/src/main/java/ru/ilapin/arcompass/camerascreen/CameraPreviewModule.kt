package ru.ilapin.arcompass.camerascreen

import android.content.Context
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
    fun provideController(context: Context, presenter: CameraPreviewPresenter) =
            CameraPreviewController(context, presenter)
}