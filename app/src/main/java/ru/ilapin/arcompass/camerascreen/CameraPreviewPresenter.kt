package ru.ilapin.arcompass.camerascreen

import android.hardware.Camera
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ru.ilapin.arcompass.R


class CameraPreviewPresenter(rootView: View) {

    @BindView(R.id.container)
    lateinit var containerView: ViewGroup

    @BindView(R.id.error_message)
    lateinit var errorMessageView: TextView

    private var cameraPreview: CameraPreview? = null

    init {
        ButterKnife.bind(this, rootView)
    }

    fun showError(errorMessage: String) {
        errorMessageView.visibility = View.VISIBLE
        errorMessageView.text = errorMessage
    }

    fun startCameraPreview(camera: Camera) {
        errorMessageView.visibility = View.GONE

        stopCameraPreview()
        val previewSize = camera.parameters.supportedPreviewSizes
                .sortedWith(Comparator<Camera.Size> { o1, o2 -> o1.width - o2.width })
                .last()

        cameraPreview = CameraPreview(containerView.context, camera)
        containerView.addView(cameraPreview)
    }

    fun stopCameraPreview() {
        errorMessageView.visibility = View.GONE

        cameraPreview?.let {
            containerView.removeView(it)
        }
    }
}