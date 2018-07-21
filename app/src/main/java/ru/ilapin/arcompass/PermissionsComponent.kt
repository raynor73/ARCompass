package ru.ilapin.arcompass

import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [PermissionsModule::class])
interface PermissionsComponent