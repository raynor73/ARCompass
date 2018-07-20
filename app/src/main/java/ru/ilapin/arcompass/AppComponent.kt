package ru.ilapin.arcompass

import dagger.Component

/**
 * @author ilapin on 20.07.18.
 */
@Component(modules = [SystemModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}