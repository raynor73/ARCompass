package ru.ilapin.arcompass

import dagger.Component
import ru.ilapin.arcompass.camerascreen.CameraPreviewComponent
import ru.ilapin.arcompass.camerascreen.CameraPreviewModule
import ru.ilapin.arcompass.compassscreen.CompassScreenComponent
import ru.ilapin.arcompass.compassscreen.CompassScreenModule
import javax.inject.Singleton

/**
 * @author ilapin on 20.07.18.
 */
@Singleton
@Component(modules = [SystemModule::class, SensorsModule::class])
interface AppComponent {

    fun compassScreenComponent(compassScreenModule: CompassScreenModule): CompassScreenComponent

    fun cameraPreviewComponent(
            cameraPreviewModule: CameraPreviewModule,
            permissionsModule: PermissionsModule
    ): CameraPreviewComponent
}