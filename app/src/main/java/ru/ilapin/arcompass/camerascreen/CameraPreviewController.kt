package ru.ilapin.arcompass.camerascreen

import android.content.Context
import android.hardware.Camera
import android.view.Surface
import android.view.WindowManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.ilapin.arcompass.R

class CameraPreviewController(private val context: Context, private val windowManager: WindowManager, private val presenter: CameraPreviewPresenter) {

    private var camera: Camera? = null

    private var cameraId = -1

    private val cameraRetriever = Observable
            .create<Camera> { emitter ->
                try {
                    val numberOfCameras = Camera.getNumberOfCameras()
                    val cameraInfo = Camera.CameraInfo()
                    for (i in 0 until numberOfCameras) {
                        Camera.getCameraInfo(i, cameraInfo)
                        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                            cameraId = i
                            break
                        }
                    }
                    val camera = Camera.open(cameraId) ?: throw NoCameraFound()
                    emitter.onNext(camera)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private var isStopPreviewRequested = false

    fun startCameraPreview() {
        isStopPreviewRequested = false
        cameraRetriever.subscribe(
                { openedCamera ->
                    camera = openedCamera
                    if (!isStopPreviewRequested) {
                        val cameraInfo = Camera.CameraInfo()
                        Camera.getCameraInfo(cameraId, cameraInfo)

                        val displayRotationDegrees = when (windowManager.defaultDisplay.rotation) {
                            Surface.ROTATION_0 -> 0
                            Surface.ROTATION_90 -> 90
                            Surface.ROTATION_180 -> 180
                            Surface.ROTATION_270 -> 270
                            else -> throw IllegalStateException(
                                    "Unknown display rotation: ${windowManager.defaultDisplay.rotation}"
                            )
                        }
                        val displayOrientation = (cameraInfo.orientation - displayRotationDegrees + 360) % 360
                        openedCamera.setDisplayOrientation(displayOrientation)
                        presenter.startCameraPreview(openedCamera, displayOrientation)
                    } else {
                        openedCamera.release()
                    }
                },
                { t ->
                    when (t) {
                        is NoCameraFound -> presenter.showError(context.getString(R.string.camera_not_found))
                        else -> presenter.showError(context.getString(R.string.error_opening_camera))
                    }
                }
        )
    }

    fun stopCameraPreview() {
        isStopPreviewRequested = true
        presenter.stopCameraPreview()
        camera?.release()
    }

    fun onCameraAccessForbidden() {
        presenter.showError(context.getString(R.string.camera_permission_required))
    }
}