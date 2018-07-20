package ru.ilapin.arcompass

import android.support.multidex.MultiDexApplication

/**
 * @author ilapin on 20.07.18.
 */
class App : MultiDexApplication() {

    companion object {

        private lateinit var appComponent_: AppComponent

        @JvmStatic
        val appComponent = appComponent_
    }

    override fun onCreate() {
        super.onCreate()

        appComponent_ = DaggerAppComponent.builder().systemModule(SystemModule(this)).build()
    }
}