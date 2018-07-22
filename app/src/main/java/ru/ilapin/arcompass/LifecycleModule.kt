package ru.ilapin.arcompass

import android.support.v7.app.AppCompatActivity
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import dagger.Module
import dagger.Provides

@Module
class LifecycleModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    fun privideLifecycleProvider() = AndroidLifecycle.createLifecycleProvider(activity)
}