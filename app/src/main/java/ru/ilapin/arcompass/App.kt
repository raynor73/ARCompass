package ru.ilapin.arcompass

import android.os.StrictMode
import android.support.multidex.MultiDexApplication

/**
 * @author ilapin on 20.07.18.
 */
class App : MultiDexApplication() {

    companion object {

        private lateinit var appComponent_: AppComponent

        @JvmStatic
        val appComponent
            get() = appComponent_
    }

    override fun onCreate() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())

        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())

        super.onCreate()

        appComponent_ = DaggerAppComponent.builder().systemModule(SystemModule(this)).build()
    }
}