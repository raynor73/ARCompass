package ru.ilapin.arcompass.camerascreen

import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.io.IOException


class CameraPreview(
        context: Context,
        private val camera: Camera,
        private val displayOrientation: Int
) : SurfaceView(context), SurfaceHolder.Callback {

    companion object {

        private const val TAG = "CameraPreview"
    }

    private val previewSize = camera.parameters.supportedPreviewSizes
            .sortedWith(Comparator<Camera.Size> { o1, o2 -> o1.width - o2.width })
            .last()

    init {
        holder.addCallback(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val isDisplayRotated = displayOrientation == 90 || displayOrientation == 270
        val previewWidth = if (isDisplayRotated) previewSize.height else previewSize.width
        val previewHeight = if (isDisplayRotated) previewSize.width else previewSize.height
        val previewAspectRatio = previewWidth.toFloat() / previewHeight.toFloat()

        if (measuredWidth > measuredHeight) {
            setMeasuredDimension((measuredHeight * previewAspectRatio).toInt(), measuredHeight)
        } else {
            setMeasuredDimension(measuredWidth, (measuredWidth / previewAspectRatio).toInt())
        }
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
            val parameters = camera.parameters
            parameters.setPreviewSize(previewSize.width, previewSize.height)
            camera.startPreview()
        } catch (e: IOException) {
            Log.e(TAG, "Error setting preview display", e)
        }
    }
}