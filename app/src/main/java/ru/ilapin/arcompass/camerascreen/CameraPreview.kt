package ru.ilapin.arcompass.camerascreen

import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.io.IOException


class CameraPreview(context: Context, private val camera: Camera) : SurfaceView(context), SurfaceHolder.Callback {

    companion object {

        private const val TAG = "CameraPreview"
    }

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d("!@#", "surfaceChanged")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // do nothing, camera is freed in controller
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
            camera.setPreviewDisplay(holder)
            camera.startPreview()
        } catch (e: IOException) {
            Log.e(TAG, "Error setting preview display", e)
        }
    }
}