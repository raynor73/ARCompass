package ru.ilapin.arcompass.camerascreen

import android.Manifest
import android.arch.lifecycle.Lifecycle
import android.content.Context
import android.hardware.Camera
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import ru.ilapin.arcompass.R

class CameraPreviewController(
        private val context: Context,
        private val presenter: CameraPreviewPresenter,
        private val rxPermissions: RxPermissions,
        private val lifecycleProvider: LifecycleProvider<Lifecycle.Event>
) {

    private var camera: Camera? = null

    private val cameraRetriever = Observable
            .create<Camera> { emitter ->
                try {
                    val camera = Camera.open() ?: throw NoCameraFound()
                    emitter.onNext(camera)
                    emitter.onComplete()

                    if (emitter.isDisposed) {
                        camera.release()
                    }
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private var cameraSubscription: Disposable? = null

    init {
        Observable.combineLatest(
                rxPermissions.request(Manifest.permission.CAMERA),
                lifecycleProvider.lifecycle(),
                BiFunction<Boolean, Lifecycle.Event, Pair<Boolean, Lifecycle.Event>> { p, e -> Pair(p, e) }
        ).subscribe { p ->
            if (p.first && p.second == Lifecycle.Event.ON_RESUME) {
                releaseCamera()
                cameraSubscription = cameraRetriever.subscribe(
                        { openedCamera ->
                            camera = openedCamera
                            presenter.showCameraPreview()
                        },
                        { t ->
                            when (t) {
                                is NoCameraFound -> presenter.showError(context.getString(R.string.camera_not_found))
                                else -> presenter.showError(context.getString(R.string.error_opening_camera))
                            }
                        }
                )
            }
            if (p.second == Lifecycle.Event.ON_PAUSE) {
                releaseCamera()
            }
        }
    }

    private fun releaseCamera() {
        cameraSubscription?.dispose()
        camera?.release()
    }
}