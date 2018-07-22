package ru.ilapin.arcompass.camerascreen

import dagger.Subcomponent
import ru.ilapin.arcompass.PerActivity

@Subcomponent(modules = [CameraPreviewModule::class])
@PerActivity
interface CameraPreviewComponent {

    fun inject(activity: CameraPreviewActivity)
}