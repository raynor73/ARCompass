package ru.ilapin.arcompass.camerascreen

import dagger.Subcomponent

@Subcomponent(modules = [CameraModule::class])
interface CameraComponent {

    fun inject(activity: CameraActivity)
}