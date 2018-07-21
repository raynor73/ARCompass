package ru.ilapin.arcompass.compassscreen

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ru.ilapin.arcompass.R
import ru.ilapin.arcompass.widgets.OrientationView
import ru.ilapin.common.math.Vector4f

class CompassScreenPresenter(rootView: View) {

    @BindView(R.id.error_messages_container)
    lateinit var errorMessagesContainer: View

    @BindView(R.id.accelerometer_error_message)
    lateinit var accelerometerErrorView: TextView

    @BindView(R.id.magnetic_field_error_message)
    lateinit var magneticFieldErrorView: TextView

    @BindView(R.id.orientation_view)
    lateinit var orientationView: OrientationView

    init {
        ButterKnife.bind(this, rootView)
    }

    fun showError(accelerometerError: String?, magneticFieldError: String?) {
        accelerometerErrorView.text = accelerometerError
        magneticFieldErrorView.text = magneticFieldError

        accelerometerErrorView.visibility = if (accelerometerError != null) View.VISIBLE else View.GONE
        magneticFieldErrorView.visibility = if (magneticFieldError != null) View.VISIBLE else View.GONE

        errorMessagesContainer.visibility = View.VISIBLE
        orientationView.visibility = View.GONE
    }

    fun showReadings() {
        errorMessagesContainer.visibility = View.GONE
        orientationView.visibility = View.VISIBLE
    }

    fun drawVector(vector: Vector4f) {
        System.arraycopy(vector.values, 0, orientationView.vector.values, 0, vector.values.size)
        orientationView.invalidate()
    }
}