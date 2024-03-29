package ru.ilapin.arcompass.camerascreen

import android.content.Context
import android.view.View
import android.view.WindowManager
import dagger.Module
import dagger.Provides
import ru.ilapin.arcompass.PerActivity

@Module
class CameraPreviewModule(private val rootView: View, private val windowManager: WindowManager) {

    @Provides
    @PerActivity
    fun providePresenter() = CameraPreviewPresenter(rootView)

    @Provides
    @PerActivity
    fun provideController(context: Context, presenter: CameraPreviewPresenter) =
            CameraPreviewController(context, windowManager, presenter)
}