package ru.ilapin.arcompass

import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module
class PermissionsModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    fun provideRxPermissions() = RxPermissions(activity)
}