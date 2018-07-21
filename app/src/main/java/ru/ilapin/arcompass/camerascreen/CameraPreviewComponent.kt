package ru.ilapin.arcompass.camerascreen

import dagger.Subcomponent
import ru.ilapin.arcompass.PerActivity
import ru.ilapin.arcompass.PermissionsModule

@Subcomponent(modules = [CameraPreviewModule::class, PermissionsModule::class])
@PerActivity
interface CameraPreviewComponent {

    fun inject(activity: CameraPreviewActivity)
}