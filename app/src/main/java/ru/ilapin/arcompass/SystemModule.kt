package ru.ilapin.arcompass

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author ilapin on 20.07.18.
 */
@Module
class SystemModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context
}