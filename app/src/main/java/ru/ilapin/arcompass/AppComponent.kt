package ru.ilapin.arcompass

import dagger.Component
import ru.ilapin.arcompass.camerascreen.CameraComponent
import ru.ilapin.arcompass.camerascreen.CameraModule
import javax.inject.Singleton

/**
 * @author ilapin on 20.07.18.
 */
@Singleton
@Component(modules = [SystemModule::class, SensorsModule::class, CameraModule::class])
interface AppComponent {

    fun compassComponent(compassModule: CompassModule): CompassComponent

    fun permissionsComponent(permissionsModule: PermissionsModule): PermissionsComponent

    fun cameraComponent(): CameraComponent
}