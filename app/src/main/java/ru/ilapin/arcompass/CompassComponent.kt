package ru.ilapin.arcompass

import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [CompassModule::class])
interface CompassComponent {

    fun inject(activity: MainActivity)
}