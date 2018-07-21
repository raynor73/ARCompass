package ru.ilapin.arcompass.compassscreen

import dagger.Subcomponent
import ru.ilapin.arcompass.PerActivity

@PerActivity
@Subcomponent(modules = [CompassScreenModule::class])
interface CompassScreenComponent {

    fun inject(activity: MainActivity)
}