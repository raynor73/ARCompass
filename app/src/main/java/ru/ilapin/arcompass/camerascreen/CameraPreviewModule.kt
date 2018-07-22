package ru.ilapin.arcompass.camerascreen

import android.arch.lifecycle.Lifecycle
import android.content.Context
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.LifecycleProvider
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
    fun provideController(
            context: Context,
            presenter: CameraPreviewPresenter,
            rxPermissions: RxPermissions,
            lifecycleProvider: LifecycleProvider<Lifecycle.Event>
    ) = CameraPreviewController(context, presenter, rxPermissions, lifecycleProvider)
}