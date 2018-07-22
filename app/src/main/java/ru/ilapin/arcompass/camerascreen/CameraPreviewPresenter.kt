package ru.ilapin.arcompass.camerascreen

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ru.ilapin.arcompass.R

class CameraPreviewPresenter(rootView: View) {

    @BindView(R.id.error_message)
    lateinit var errorMessageView: TextView

    init {
        ButterKnife.bind(this, rootView)
    }

    fun showError(errorMessage: String) {
        errorMessageView.visibility = View.VISIBLE
        errorMessageView.text = errorMessage
    }

    fun showCameraPreview() {
        errorMessageView.visibility = View.GONE
    }
}