package ru.ilapin.arcompass.camerascreen

import android.content.Context
import android.hardware.Camera
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.ilapin.arcompass.R

class CameraPreviewController(private val context: Context, private val presenter: CameraPreviewPresenter) {

    private var camera: Camera? = null

    private val cameraRetriever = Observable
            .create<Camera> { emitter ->
                try {
                    val camera = Camera.open() ?: throw NoCameraFound()
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
                        presenter.startCameraPreview(openedCamera)
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