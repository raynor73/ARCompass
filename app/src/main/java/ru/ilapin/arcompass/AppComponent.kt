package ru.ilapin.arcompass

import dagger.Component
import javax.inject.Singleton

/**
 * @author ilapin on 20.07.18.
 */
@Singleton
@Component(modules = [SystemModule::class])
interface AppComponent {

    fun compassComponent(compassModule: CompassModule): CompassComponent
}